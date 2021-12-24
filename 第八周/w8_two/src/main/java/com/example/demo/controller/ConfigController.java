package com.example.demo.controller;

import com.example.demo.dao.ConfigDao;
import com.example.demo.pojo.TConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConfigController {
    @Autowired
    private ConfigDao configDao;

    @RequestMapping(value = "/listConfig", method = RequestMethod.GET)
    public List<TConfig> getConfig() {
        return this.configDao.findAll();
    }
}