package com.helloKaviraj.calendarassistant;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarAssistantController {
    private static final Logger logger = LoggerFactory.getLogger(CalendarAssistantController.class);

    @Autowired
    private BookMeetingRequest bookMeetingRequest;

    @Autowired
    private MeetingScheduler meetingScheduler;
    @Autowired
    private FreeSlotsFinder freeSlotsFinder;

    @Autowired
    private MeetingConflictChecker meetingConflictChecker;

    @PostMapping("/book-meeting")
    public ResponseEntity<?> bookMeeting(@RequestBody BookMeetingRequest request) {
        logger.info("BookMeetingRequest: {}", request);
        boolean booked = meetingScheduler.bookMeeting(request.getOwner(), request.getStartTime(), request.getEndTime());
        if (booked) {
            return ResponseEntity.ok("Meeting booked successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Meeting cannot be booked");
        }
    }


    @PostMapping("/find-free-slots")
    public List<LocalDateTime> findFreeSlots(@RequestBody FindFreeSlotsRequest request) {
        CalendarOwner owner1 = new CalendarOwner(request.getOwner1());
        CalendarOwner owner2 = new CalendarOwner(request.getOwner2());
        Duration duration = Duration.ofMinutes(request.getDurationMinutes());
        return freeSlotsFinder.findFreeSlots(owner1, owner2, duration);
    }

    @PostMapping("/check-meeting-conflict")
    public boolean checkMeetingConflict(@RequestBody MeetingConflictRequest request) {
        CalendarOwner owner = new CalendarOwner(request.getOwner());
        List<String> participants = request.getParticipants();
        LocalDateTime start = request.getStartTime();
        LocalDateTime end = request.getEndTime();
        return meetingConflictChecker.hasConflict(owner, participants, start, end);
    }
    @GetMapping("/list-meetings")
    public ResponseEntity<List<Meeting>> listMeetings() {
        List<Meeting> meetings = meetingScheduler.getMeetings(LocalDateTime.now().with(LocalTime.MIN), LocalDateTime.now().with(LocalTime.MAX).plusDays(7));
        return ResponseEntity.ok(meetings);
    }


}
