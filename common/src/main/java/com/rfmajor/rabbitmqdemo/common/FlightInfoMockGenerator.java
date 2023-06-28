package com.rfmajor.rabbitmqdemo.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
public class FlightInfoMockGenerator {
    private long nextId = 0;
    private final Random random;

    private static final String[] CITIES = {"Cracow", "London", "Madrid", "Paris",
            "Munich", "Marseille", "Budapest", "Lisbon", "Porto", "Manchester", "Prague"};
    private static final int MIN_HOURS = 2;
    private static final int MAX_HOURS = 6;

    public FlightInfo generate() {
        String[] cities = getRandomToAndFromCities();
        LocalDateTime[] times = getRandomDepartureAndArrival();
        return FlightInfo.builder()
                .id(nextId++)
                .fromCity(cities[0])
                .toCity(cities[1])
                .departureTime(times[0])
                .arrivalTime(times[1])
                .build();
    }

    private String[] getRandomToAndFromCities() {
        int fromCityIndex = random.nextInt(CITIES.length);
        int toCityIndex = fromCityIndex;
        while (fromCityIndex == toCityIndex) {
            toCityIndex = random.nextInt(CITIES.length);
        }
        return new String[]{CITIES[fromCityIndex], CITIES[toCityIndex]};
    }

    private LocalDateTime[] getRandomDepartureAndArrival() {
        int daysFromNow = random.nextInt(30, 180);
        int hoursFromMidnight = random.nextInt(0, 24);
        LocalDateTime departure = LocalDate.now().plusDays(daysFromNow).atStartOfDay().withHour(hoursFromMidnight);
        int flightHours = random.nextInt(MIN_HOURS, MAX_HOURS);
        LocalDateTime arrival = departure.plusHours(flightHours);
        return new LocalDateTime[]{departure, arrival};
    }
}
