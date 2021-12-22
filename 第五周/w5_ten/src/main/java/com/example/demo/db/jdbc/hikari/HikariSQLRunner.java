package com.example.demo.db.jdbc.hikari;

import com.example.demo.pojo.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class HikariSQLRunner implements InitializingBean {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void afterPropertiesSet()  {
		String sql = "select * from user";
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
		for(User user:users){
			System.out.println(user.toString());
		}

	}
}
