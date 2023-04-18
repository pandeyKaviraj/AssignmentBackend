package com.helloKaviraj.calendarassistant;


import java.time.LocalDateTime;

public class CalendarOwner {

    private String name;
    private LocalDateTime[] calendar;

    public CalendarOwner(String name) {
        this.name = name;
        this.calendar = new LocalDateTime[24 * 7]; // One week calendar
    }

    public String getName() {
        return name;
    }

    public LocalDateTime[] getCalendar() {
        return calendar;
    }

    public void bookMeeting(LocalDateTime start, LocalDateTime end) {
        for (int i = start.getHour(); i < end.getHour(); i++) {
            calendar[(start.getDayOfWeek().getValue() - 1) * 24 + i] = start.plusHours(i - start.getHour());
        }
    }
}

