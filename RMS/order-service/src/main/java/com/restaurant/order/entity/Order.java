package com.restaurant.order.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;

    private String tableId;
    private String waiterId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    private BigDecimal totalAmount = BigDecimal.ZERO;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum OrderStatus {
        // Kitchen flow
        PENDING,        // Waiter placed, waiting for chef
        ACCEPTED,       // Chef accepted order
        PREPARING,      // Chef is cooking
        READY,          // Food ready for service

        // Service flow
        SERVED,         // Waiter served food (not billed yet)
        BILLED,         // Cashier created bill (not served yet)
        COMPLETED,      // Both served AND billed
        CANCELLED       // Order cancelled
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}