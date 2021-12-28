package com.example.demo;

import com.example.demo.pojo.Message;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Date;

@SpringBootApplication
@EnableJms
public class DemoApplication {
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    public static void main(String[] args) {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

       /* //Get JMS template bean reference
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        Queue queue = (Queue) context.getBean("test_queue");
        Topic topic = (Topic) context.getBean("test_topic");

        // Send a message
        System.out.println("Sending a queue.");
        //jmsTemplate.convertAndSend("jms.message.endpoint", new Message(1001L, "test body", new Date()));
        System.out.println(queue);
        System.out.println(topic);
        //jmsTemplate.convertAndSend(queue, "queue---------");
        System.out.println("sending a topic.");
        jmsTemplate.convertAndSend(topic, "topic----------");*/

    }


}
