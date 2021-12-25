package com.example.demo.controller;

import com.example.demo.service.XaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    XaService xaService;


    @GetMapping("/save")
    public void save()  {
        xaService.addUsersWithError();
    }

}
