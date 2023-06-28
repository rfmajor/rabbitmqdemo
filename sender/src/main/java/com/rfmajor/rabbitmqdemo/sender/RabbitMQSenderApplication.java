package com.rfmajor.rabbitmqdemo.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RabbitMQSenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQSenderApplication.class, args);
    }
}
