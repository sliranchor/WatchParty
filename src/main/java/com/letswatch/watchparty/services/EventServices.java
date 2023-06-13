package com.letswatch.watchparty.services;

import com.letswatch.watchparty.dto.EventDto;

import java.util.List;

public interface EventServices {
    void createEvent(Long partyId, EventDto eventDto);
    List<EventDto> listAllEvents();
    EventDto findEventById(long eventId);
    void updateEvent(EventDto eventDto);
    void deleteEvent(Long eventId);
}
