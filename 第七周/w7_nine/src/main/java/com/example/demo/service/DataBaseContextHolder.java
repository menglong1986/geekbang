package com.example.demo.service;

import javax.sql.DataSource;

public class DataBaseContextHolder {

    //区分主从数据源
    public enum DataBaseType {
        MASTER, SLAVE
    }
    //线程局部变量
    private static final ThreadLocal<DataSource> contextHolder = new ThreadLocal<DataSource>();

    //往线程里边set数据类型
    public static void setDataSource(DataSource dataBaseType) {
        if(dataBaseType == null) {
            throw new NullPointerException();
        }
        contextHolder.set(dataBaseType);
    }

    //从容器中获取数据类型
    public static DataSource getDataSource(){
        return contextHolder.get();
    }
    //清空容器中的数据类型
    public static void clearDataBaseType(){
        contextHolder.remove();
    }
}
