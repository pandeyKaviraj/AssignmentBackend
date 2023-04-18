package com.helloKaviraj.calendarassistant;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
class MeetingSchedulerImpl implements MeetingScheduler {

    private Map<LocalDateTime, Meeting> meetings = new HashMap<>();

    @Override
    public boolean bookMeeting(String owner, LocalDateTime startTime, LocalDateTime endTime) {
        // Check if there is already a meeting scheduled during the requested time
        for (Meeting meeting : meetings.values()) {
            if (meeting.getStartTime().isBefore(endTime) && meeting.getEndTime().isAfter(startTime)) {
                return false;
            }
        }

        // Create a new meeting and add it to the meetings map
        Meeting meeting = new Meeting(owner, startTime, endTime);
        meetings.put(startTime, meeting);

        return true;
    }

    @Override
    public List<Meeting> getMeetings(LocalDateTime startTime, LocalDateTime endTime) {
        List<Meeting> meetingsInRange = new ArrayList<>();

        for (Meeting meeting : meetings.values()) {
            if (meeting.getStartTime().isAfter(startTime) && meeting.getEndTime().isBefore(endTime)) {
                meetingsInRange.add(meeting);
            }
        }

        return meetingsInRange;
    }
}
