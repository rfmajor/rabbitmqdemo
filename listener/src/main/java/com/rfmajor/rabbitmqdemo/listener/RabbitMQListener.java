package com.rfmajor.rabbitmqdemo.listener;

import com.rfmajor.rabbitmqdemo.common.FlightInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQListener {
    @RabbitListener(queues = "flight.queue")
    public void onMessage(FlightInfo flightInfo) {
        log.info("Flight: {}", flightInfo);
    }
}
