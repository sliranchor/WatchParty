package com.letswatch.watchparty.mapper;

import com.letswatch.watchparty.dto.EventDto;
import com.letswatch.watchparty.models.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto){
        return Event.builder()
                .eventId(eventDto.getEventId())
                .eventName(eventDto.getEventName())
                .eventDescription(eventDto.getEventDescription())
                .eventPhotoUrl(eventDto.getEventPhotoUrl())
                .eventCreatedOn(eventDto.getEventCreatedOn())
                .eventUpdatedOn(eventDto.getEventUpdatedOn())
                .eventStartTime(eventDto.getEventStartTime())
                .eventEndTime(eventDto.getEventEndTime())
                .party(eventDto.getParty())
                .build();
    }

    public static EventDto mapToEventDto(Event event){
        return EventDto.builder()
                .eventId(event.getEventId())
                .eventName(event.getEventName())
                .eventDescription(event.getEventDescription())
                .eventPhotoUrl(event.getEventPhotoUrl())
                .eventCreatedOn(event.getEventCreatedOn())
                .eventUpdatedOn(event.getEventUpdatedOn())
                .eventStartTime(event.getEventStartTime())
                .eventEndTime(event.getEventEndTime())
                .party(event.getParty())
                .build();
    }
}
