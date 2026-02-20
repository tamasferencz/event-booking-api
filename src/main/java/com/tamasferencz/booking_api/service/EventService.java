package com.tamasferencz.booking_api.service;

import com.tamasferencz.booking_api.entity.Event;
import com.tamasferencz.booking_api.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return Optional.of(eventRepository.findById(id).orElseThrow(() -> new EventNotFound("Event with id %d not found.".formatted(id))));
    }

    @Transactional
    public Optional<Event> updateEvent(Long id, Event updatedEvent) {

        var event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new EventNotFound("Event with id %d not found!".formatted(id));
        }

        return event.map(existingEvent -> {
            existingEvent.setName(updatedEvent.getName());
            existingEvent.setLocation(updatedEvent.getLocation());
            existingEvent.setDate(updatedEvent.getDate());
            existingEvent.setTotalSeats(updatedEvent.getTotalSeats());

            return eventRepository.save(existingEvent);
        });
    }

    public void deleteEvent(Long id) {
        var event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new EventNotFound("Event with id %d not found!".formatted(id));
        }
        eventRepository.deleteById(id);
    }

    public Optional<Event> getEventsByLocation(String location){
        var event = eventRepository.findByLocation(location);

        if(event.isEmpty()){
            throw new EventNotFound("Event with location %s not found!".formatted(location));
        }

        return event;
    }

    public List<Event> getEventsBetweenDates(LocalDate startDate, LocalDate endDate){
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        var event = eventRepository.findByDateBetween(startDateTime, endDateTime);

        if(event.isEmpty()){
            throw new EventNotFound("Event with dates starts at %s and ends with %s not found!".formatted(startDate,endDate));
        }

        return event;
    }
}
