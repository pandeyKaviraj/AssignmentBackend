package com.helloKaviraj.calendarassistant;
import java.time.Duration;
import java.time.LocalDateTime;

public class DurationExample {
    public static void main(String[] args) {
        LocalDateTime startDateTime = LocalDateTime.of(2023, 4, 19, 10, 30);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 4, 19, 11, 30);
        Duration duration = Duration.between(startDateTime, endDateTime);
        long minutes = duration.toMinutes();
        System.out.println("Duration in minutes: " + minutes);
    }
}
