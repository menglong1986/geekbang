package com.example.demo.service;

import com.example.demo.model.User;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class XaDemoService implements InitializingBean {

    @Resource
    private UserService userService;


    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    public List<User> addTenUser() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("[" + i + "]");
            user.setMobile("1377000000" + i);
            userService.save(user);
            list.add(user);
        }
        return list;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        addTenUser();
    }

}
