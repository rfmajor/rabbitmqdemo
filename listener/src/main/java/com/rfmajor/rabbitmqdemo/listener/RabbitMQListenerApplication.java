package com.rfmajor.rabbitmqdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
@EnableRabbit
public class RabbitMQListenerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQListenerApplication.class, args);
    }
}
