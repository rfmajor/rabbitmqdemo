package com.rfmajor.rabbitmqdemo.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Builder
public class FlightInfo implements Serializable {
    private long id;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String fromCity;
    private String toCity;

    private static final String FORMAT_STRING = "{%d;departure:%s - %s;arrival:%s - %s}";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Serial
    private static final long serialVersionUID = -1L;

    @Override
    public String toString() {
        return String.format(
                FORMAT_STRING,
                id, fromCity, departureTime.format(DATE_FORMATTER), toCity, arrivalTime.format(DATE_FORMATTER)
        );
    }
}
