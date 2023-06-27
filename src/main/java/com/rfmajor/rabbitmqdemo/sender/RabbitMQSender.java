package com.rfmajor.rabbitmqdemo.sender;

import com.rfmajor.rabbitmqdemo.common.FlightInfo;
import com.rfmajor.rabbitmqdemo.common.FlightInfoMockGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQSender implements Runnable {
    private static final int DURATION_SECONDS = 3600;
    private static final int MESSAGES_PER_SEC = 10;

    private final RabbitTemplate rabbitTemplate;
    private final FlightInfoMockGenerator flightInfoGenerator;


    @Override
    public void run() {
        try {
            sendMessages();
        } catch (InterruptedException e) {
            log.error("A thread was interrupted, message: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void sendMessages() throws InterruptedException {
        for (int i = 0; i < DURATION_SECONDS; i++) {
            for (int j = 0; j < MESSAGES_PER_SEC; j++) {
                FlightInfo flightInfo = flightInfoGenerator.generate();
                rabbitTemplate.convertAndSend(flightInfo);
                TimeUnit.MILLISECONDS.sleep(1000 / MESSAGES_PER_SEC);
            }
        }
    }
}
