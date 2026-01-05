package com.restaurant.order.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String itemId;
    private String itemName;
    private Integer quantity;
    private BigDecimal itemPrice;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus status = OrderItemStatus.PENDING;

    public enum OrderItemStatus {
        PENDING, ACCEPTED, COMPLETED
    }
}
