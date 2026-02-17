package com.tamasferencz.booking_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*; // database
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime; // time

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "The unique ID of the event", example = "1")
    private Long id;
    @Schema(description = "The title of the event", example = "Java Spring Boot Workshop")
    private String name;
    @Schema(description = "Location of the event", example = "Budapest, Heroes' Square")
    private String location;
    @Schema(description = "Date and time of the event", example = "2026-05-20T14:00:00")
    private LocalDateTime date;
    @Schema(description = "Total number of seats available", example = "100")
    private int totalSeats;
}
