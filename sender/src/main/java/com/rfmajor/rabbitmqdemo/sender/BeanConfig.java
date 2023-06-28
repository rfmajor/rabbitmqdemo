package com.rfmajor.rabbitmqdemo.sender;

import com.rfmajor.rabbitmqdemo.common.FlightInfoMockGenerator;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rfmajor.rabbitmqdemo.common.Utils;

import java.util.Random;

@Configuration
public class BeanConfig {
    @Bean
    public ConnectionFactory senderConnectionFactory() {
        return Utils.createConnectionFactory();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setExchange("flight.direct");
        return template;
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public FlightInfoMockGenerator flightInfoGenerator(Random random) {
        return new FlightInfoMockGenerator(random);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RabbitMQSender rabbitMQSender) {
        return args -> rabbitMQSender.sendMessages();
    }
}
