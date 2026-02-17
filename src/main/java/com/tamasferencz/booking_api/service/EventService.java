package com.tamasferencz.booking_api.service;

import com.tamasferencz.booking_api.entity.Event;
import com.tamasferencz.booking_api.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
}
