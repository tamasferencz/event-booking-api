package com.tamasferencz.booking_api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*; // database
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "speakers")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "The unique ID of the speaker", example = "1")
    private Long id;
    @Schema(description = "The name of the Speaker", example = "John Doe")
    private String name;
    @Schema(description = "The email address of the Speaker", example = "example@gmail.com")
    private String email;
    @Schema(description = "A short biography of the Speaker", example = "Hello, My name is Example and I'm a PHD student in Computer Science!")
    private String bio;
    @Schema(description = "Rating from viewers of the Speaker (1-10)", example = "4.5")
    private double rating;
    @Schema(description = "Ticket price in EUR", example = "8.2")
    private double fee;
}
