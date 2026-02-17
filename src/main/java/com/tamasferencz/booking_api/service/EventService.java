package com.tamasferencz.booking_api.service;

import com.tamasferencz.booking_api.entity.Event;
import com.tamasferencz.booking_api.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public Optional<Event> getEventById(Long id){
        return eventRepository.findById(id);
    }

    public Optional<Event> updateEvent(Long id, Event updatedEvent) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    existingEvent.setName(updatedEvent.getName());
                    existingEvent.setLocation(updatedEvent.getLocation());
                    existingEvent.setDate(updatedEvent.getDate());
                    existingEvent.setTotalSeats(updatedEvent.getTotalSeats());

                    return eventRepository.save(existingEvent);
                });
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
}
