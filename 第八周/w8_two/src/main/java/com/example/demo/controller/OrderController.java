package com.example.demo.controller;

import com.example.demo.dao.OrderDao;
import com.example.demo.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class OrderController {
    @Autowired
    private OrderDao orderDao;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public Optional<Order> getOrderById(@RequestParam("id") Long id) {
        return this.orderDao.findById(id);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> orders() {
        return this.orderDao.findAll();
    }

    @RequestMapping(value = "/order/save", method = RequestMethod.GET)
    public Order saveOrder() {
        int userId = Math.abs(new Random().nextInt());
        String name = "[" + userId + "]";
        Order order = new Order();
        order.setName(name);
        order.setUserId(userId);
        return this.orderDao.save(order);
    }
}

