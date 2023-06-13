package com.letswatch.watchparty.services.implementations;

import com.letswatch.watchparty.dto.EventDto;
import com.letswatch.watchparty.dto.PartyDto;
import com.letswatch.watchparty.models.Event;
import com.letswatch.watchparty.models.Party;
import com.letswatch.watchparty.repository.EventRepository;
import com.letswatch.watchparty.repository.PartyRepository;
import com.letswatch.watchparty.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.letswatch.watchparty.mapper.EventMapper.mapToEvent;
import static com.letswatch.watchparty.mapper.EventMapper.mapToEventDto;
import static com.letswatch.watchparty.mapper.PartyMapper.maptoParty;

@Service
public class EventServiceImplementation implements EventServices {

    private EventRepository eventRepository;
    private PartyRepository partyRepository;

    @Autowired
    public EventServiceImplementation(EventRepository eventRepository, PartyRepository partyRepository) {
        this.eventRepository = eventRepository;
        this.partyRepository = partyRepository;
    }

    @Override
    public void createEvent(Long partyId, EventDto eventDto) {
        Party party = partyRepository.findById(partyId).get();
        Event event = mapToEvent(eventDto);
        event.setParty(party);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> listAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(event -> mapToEventDto(event))
                .collect(Collectors.toList());
    }

    @Override
    public EventDto findEventById(long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId){
        eventRepository.deleteById(eventId);
    }


}
