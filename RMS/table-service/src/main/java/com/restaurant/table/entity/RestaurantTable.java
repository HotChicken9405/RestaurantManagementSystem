package com.restaurant.table.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "restaurant_tables")
@Data
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tableId;

    @Column(unique = true, nullable = false)
    private String tableNumber;

    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

    public enum TableStatus {
        AVAILABLE, RESERVED, OCCUPIED
    }
}
