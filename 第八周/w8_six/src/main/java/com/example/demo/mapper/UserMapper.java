package com.example.demo.mapper;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper  {

    void save(User user);
}
