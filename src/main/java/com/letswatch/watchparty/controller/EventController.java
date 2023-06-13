package com.letswatch.watchparty.controller;

import com.letswatch.watchparty.dto.PartyDto;
import com.letswatch.watchparty.services.EventServices;
import jakarta.validation.Valid;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.letswatch.watchparty.dto.EventDto;
import com.letswatch.watchparty.models.Event;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    private EventServices eventServices;

    public EventController(EventServices eventServices){
        this.eventServices = eventServices;
    }

    @GetMapping("/events/{partyId}/new")
    public String createEvent(@PathVariable("partyId") Long partyId, Model model){
        Event event = new Event();
        model.addAttribute("partyId", partyId);
        model.addAttribute("event", event);
        return "create-events";

    }

    @PostMapping("/events/{partyId}")
    public String postEvent(@PathVariable("partyId") Long partyId,
                            @ModelAttribute("event") EventDto eventDto,
                            Model model){

        eventServices.createEvent(partyId, eventDto);
        return "redirect:/parties/" + partyId;

    }

    @GetMapping("/events")
    public String allEvents(Model model){
        List<EventDto> events = eventServices.listAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{eventId}")
    public String eventDetails(@PathVariable("eventId") Long eventId, Model model){
        EventDto eventDto = eventServices.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "event-details";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEvent(@PathVariable("eventId") Long eventId, Model model){
        EventDto event = eventServices.findEventById(eventId);
        model.addAttribute("event", event);
        return "event-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId, @Valid @ModelAttribute("event") EventDto event, BindingResult result, Model model  ){
        if(result.hasErrors()){
            model.addAttribute("event", event);
            return "event-edit";
        }

        /*
        Lazy Loading to set the Party when updating
         */
        EventDto eventDto = eventServices.findEventById(eventId);
        event.setEventId(eventId);
        event.setParty(eventDto.getParty());
        eventServices.updateEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventServices.deleteEvent(eventId);
        return "redirect:/events";
    }





}
