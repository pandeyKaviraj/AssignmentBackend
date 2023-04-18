package com.helloKaviraj.calendarassistant;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingScheduler {
    boolean bookMeeting(String owner, LocalDateTime startTime, LocalDateTime endTime);
    List<Meeting> getMeetings(LocalDateTime startTime, LocalDateTime endTime);
}
