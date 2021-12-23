package com.example.demo.controller;

import com.example.demo.service.IUserService;
import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;

    @GetMapping("/list")
    public List<User> listByMaster() throws Exception {
        return iUserService.list();
    }

    @GetMapping("/save")
    public void save() throws Exception {
         iUserService.save();
    }
}
