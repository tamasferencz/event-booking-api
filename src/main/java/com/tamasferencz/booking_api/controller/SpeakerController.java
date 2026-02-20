package com.tamasferencz.booking_api.controller;

import com.tamasferencz.booking_api.entity.Speaker;
import com.tamasferencz.booking_api.service.SpeakerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/speakers")
@Tag(name = "Speaker Management", description = "Operations for creating, retrieving, updating, and deleting speakers.")
public class SpeakerController {

    private final SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    // --- 1. CREATE (POST) ---
    @Operation(
            summary = "Create a new speaker",
            description = "Creates a new speaker based on the provided data. The ID will be auto-generated."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Speaker created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data provided")
    })
    @PostMapping
    public ResponseEntity<Speaker> CreateSpeaker(@RequestBody Speaker speaker){
        Speaker savedSpeaker = speakerService.createSpeaker(speaker);
        return new ResponseEntity<>(savedSpeaker, HttpStatus.CREATED);
    }

    // --- 2. GET ALL (GET) ---
    @Operation(
            summary = "List all speakers",
            description = "Retrieves a list of all speakers currently stored in the database."
    )
    @ApiResponse(responseCode = "200", description = "List of speakers retrieved successfully")
    @GetMapping
    public List<Speaker> getAllSpeakers(){
        return speakerService.getAllSpeakers();
    }

    // --- 3. GET BY ID (GET) ---
    @Operation(
            summary = "Get speaker by ID",
            description = "Retrieves a single speaker by its unique ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Speaker found and returned"),
            @ApiResponse(responseCode = "404", description = "Speaker not found with the given ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Speaker> getSpeakerById(
            @Parameter(description = "ID of the speaker to retrieve", example = "1")
            @PathVariable Long id) {

        return speakerService.getSpeakerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- 4. UPDATE (PUT) ---
    @Operation(
            summary = "Update an existing speaker",
            description = "Updates the details of an existing speaker identified by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Speaker updated successfully"),
            @ApiResponse(responseCode = "404", description = "Speaker not found with the given ID"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Speaker> updateSpeaker(
            @Parameter(description = "ID of the speaker to update", example = "1")
            @PathVariable Long id,
            @RequestBody Speaker updatedSpeaker) {

        return speakerService.updateSpeaker(id, updatedSpeaker)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- 5. DELETE (DELETE) ---
    @Operation(
            summary = "Delete a speaker",
            description = "Permanently removes a speaker from the database by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Speaker deleted successfully (No Content)"),
            @ApiResponse(responseCode = "404", description = "Speaker not found with the given ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeaker(
            @Parameter(description = "ID of the speaker to delete", example = "1")
            @PathVariable Long id) {

        speakerService.deleteSpeaker(id);
        return ResponseEntity.noContent().build();
    }

}
