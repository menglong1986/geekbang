package com.example.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyHikariconfig {

	@Bean
	public DataSource getDataSource() {
		HikariConfig hikariConfig = new HikariConfig("/hikari.properties");
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
}
