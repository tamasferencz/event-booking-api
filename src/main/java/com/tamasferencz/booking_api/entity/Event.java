package com.tamasferencz.booking_api.entity;

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
    private Long id;

    private String name;           // name
    private String location;       // location
    private LocalDateTime date;    // when will it happen
    private int totalSeats; // seats


}
