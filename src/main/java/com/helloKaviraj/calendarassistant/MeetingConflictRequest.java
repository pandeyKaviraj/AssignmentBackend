package com.helloKaviraj.calendarassistant;
import java.time.LocalDateTime;
import java.util.List;

public class MeetingConflictRequest {
    private List<String> participants;
    private  String owner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // getters and setters

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
