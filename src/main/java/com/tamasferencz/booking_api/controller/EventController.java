package com.tamasferencz.booking_api.controller;

import com.tamasferencz.booking_api.entity.Event;
import com.tamasferencz.booking_api.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Event Management", description = "Operations for creating, retrieving, updating, and deleting events.")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // --- 1. CREATE (POST) ---
    @Operation(
            summary = "Create a new event",
            description = "Creates a new event based on the provided data. The ID will be auto-generated."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data provided")
    })
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event savedEvent = eventService.createEvent(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    // --- 2. GET ALL (GET) ---
    @Operation(
            summary = "List all events",
            description = "Retrieves a list of all events currently stored in the database."
    )
    @ApiResponse(responseCode = "200", description = "List of events retrieved successfully")
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    // --- 3. GET BY ID (GET) ---
    @Operation(
            summary = "Get event by ID",
            description = "Retrieves a single event by its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found and returned"),
            @ApiResponse(responseCode = "404", description = "Event not found with the given ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(
            @Parameter(description = "ID of the event to retrieve", example = "1")
            @PathVariable Long id) {

        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- 4. UPDATE (PUT) ---
    @Operation(
            summary = "Update an existing event",
            description = "Updates the details of an existing event identified by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found with the given ID"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @Parameter(description = "ID of the event to update", example = "1")
            @PathVariable Long id,
            @RequestBody Event updatedEvent) {

        return eventService.updateEvent(id, updatedEvent)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- 5. DELETE (DELETE) ---
    @Operation(
            summary = "Delete an event",
            description = "Permanently removes an event from the database by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event deleted successfully (No Content)"),
            @ApiResponse(responseCode = "404", description = "Event not found with the given ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(
            @Parameter(description = "ID of the event to delete", example = "1")
            @PathVariable Long id) {

        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    // --- 6. FIND BY LOCATION (GET) ---
    @Operation(
            summary = "Get events by location",
            description = "Retrieves a list of events happening at a specific location."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of events retrieved successfully (can be empty if none found)")
    })
    @GetMapping("/location/{location}")
    public ResponseEntity<Optional<Event>> getEventsByLocation(
            @Parameter(description = "Location of the events to retrieve", example = "Budapest")
            @PathVariable String location) {

        Optional<Event> events = eventService.getEventsByLocation(location);

        return ResponseEntity.ok(events);
    }
}

