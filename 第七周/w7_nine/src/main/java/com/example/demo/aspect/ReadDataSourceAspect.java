package com.example.demo.aspect;

import com.example.demo.datasource.config.MyDataSourceManager;
import com.example.demo.service.DataBaseContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Aspect
public class ReadDataSourceAspect {

    @Qualifier(value = "slaveDataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired
    MyDataSourceManager myDataSourceManager;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.example.demo.annotation.ReadOnly)")
    public void read() {
    }

    @Around("read()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("data source change......");
        Object[] argv = point.getArgs();

        DataBaseContextHolder.setDataSource(myDataSourceManager.getSlaveDataSource());
        point.proceed(argv);
    }

}
