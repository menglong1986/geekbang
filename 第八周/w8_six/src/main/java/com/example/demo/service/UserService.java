package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {


    @Autowired
    UserMapper userMapper;

    public void save(User user) {
        userMapper.save(user);
    }
}
