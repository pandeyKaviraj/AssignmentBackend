package com.helloKaviraj.calendarassistant;

import java.time.LocalDateTime;

public class Meeting {
    private String owner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Meeting(String owner, LocalDateTime startTime, LocalDateTime endTime) {
        this.owner = owner;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean overlaps(Meeting other) {
        return this.getStartTime().isBefore(other.getEndTime()) && other.getStartTime().isBefore(this.getEndTime());
    }
}
