package com.restaurant.table.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String reservationId;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    private String customerName;
    private Integer partySize;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private String waiterId;
    private LocalDateTime reservationTime = LocalDateTime.now();

    public enum ReservationStatus {
        CONFIRMED, CANCELLED
    }
}