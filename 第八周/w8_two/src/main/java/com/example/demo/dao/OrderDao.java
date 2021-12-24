package com.example.demo.dao;

import com.example.demo.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderDao extends JpaRepository<Order, Long> {
}

