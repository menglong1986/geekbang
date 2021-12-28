package com.example.demo.service;

import com.example.demo.pojo.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class MessageReceiver {

    @JmsListener(destination = "queue")
    public void listenQuene(String msg) {
        System.out.println("接收到queue的消息：" + msg);
    }

    @JmsListener(destination = "topic")
    public void listenTopic(String msg) {
        System.out.println("接收Topic的消息：" + msg);
    }
}