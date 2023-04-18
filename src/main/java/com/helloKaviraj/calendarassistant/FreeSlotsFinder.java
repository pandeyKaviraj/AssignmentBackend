package com.helloKaviraj.calendarassistant;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class FreeSlotsFinder {

    public List<LocalDateTime> findFreeSlots(CalendarOwner owner1, CalendarOwner owner2, Duration duration) {
        List<LocalDateTime> freeSlots = new ArrayList<>();

        LocalDateTime start = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime end = start.plusDays(7);

        for (LocalDateTime slotStart = start; slotStart.isBefore(end); slotStart = slotStart.plusMinutes(30)) {
            LocalDateTime slotEnd = slotStart.plus(duration);
            boolean owner1Free = isOwnerFree(owner1, slotStart, slotEnd);
            boolean owner2Free = isOwnerFree(owner2, slotStart, slotEnd);

            if (owner1Free && owner2Free) {
                freeSlots.add(slotStart);
            }
        }

        return freeSlots;
    }

    private boolean isOwnerFree(CalendarOwner owner, LocalDateTime start, LocalDateTime end) {
        for (int i = start.getHour(); i < end.getHour(); i++) {
            if (owner.getCalendar()[(start.getDayOfWeek().getValue() - 1) * 24 + i] != null) {
                return false;
            }
        }

        return true;
    }
}
