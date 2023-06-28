package com.rfmajor.rabbitmqdemo.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.rfmajor.rabbitmqdemo.common.FlightInfo;
import com.rfmajor.rabbitmqdemo.common.FlightInfoMockGenerator;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RabbitMQSender {
    private static final int DURATION_SECONDS = 3600;
    private static final int MESSAGES_PER_SEC = 10;

    private final RabbitTemplate rabbitTemplate;
    private final FlightInfoMockGenerator flightInfoGenerator;

    public void sendMessages() throws InterruptedException {
        for (int i = 0; i < DURATION_SECONDS; i++) {
            for (int j = 0; j < MESSAGES_PER_SEC; j++) {
                FlightInfo flightInfo = flightInfoGenerator.generate();
                rabbitTemplate.convertAndSend(flightInfo);
                TimeUnit.MILLISECONDS.sleep(1000 / MESSAGES_PER_SEC);
            }
        }
    }
}
