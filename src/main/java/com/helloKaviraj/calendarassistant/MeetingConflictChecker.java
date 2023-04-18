package com.helloKaviraj.calendarassistant;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class MeetingConflictChecker {

    public boolean hasConflict(CalendarOwner owner, List<String> participants, LocalDateTime start, LocalDateTime end) {
        for (String participant : participants) {
            CalendarOwner participantOwner = new CalendarOwner(participant);
            if (isParticipantBusy(participantOwner, start, end)) {
                return true;
            }
        }

        return false;
    }


    private boolean isParticipantBusy(CalendarOwner participant, LocalDateTime start, LocalDateTime end) {
        for (int i = start.getHour(); i < end.getHour(); i++) {
            if (participant.getCalendar()[(start.getDayOfWeek().getValue() - 1) * 24 + i] != null) {
                return true;
            }
        }

        return false;
    }
}
