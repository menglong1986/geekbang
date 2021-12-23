package com.example.demo.datasource.config;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Component
public class MyDataSourceManager {

    @Resource(name = "masterDataSource")
    private DataSource masterDataSource;

    @Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;


    public DataSource getDefaultDataSource() {
        return masterDataSource;
    }

    public DataSource getSlaveDataSource() {
        return slaveDataSource;
    }
}
