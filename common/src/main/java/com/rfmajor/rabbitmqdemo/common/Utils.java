package com.rfmajor.rabbitmqdemo.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Utils {
    public static ConnectionFactory createConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setAddresses("127.0.0.1:30000,127.0.0.1:30002,127.0.0.1:30004");
        connectionFactory.setChannelCacheSize(10);
        return connectionFactory;
    }
}
