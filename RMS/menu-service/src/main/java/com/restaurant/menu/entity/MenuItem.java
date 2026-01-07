package com.restaurant.menu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String itemId;

    private String itemName;
    private String description;
    private BigDecimal price;
    private String category;
    private Boolean isAvailable = true;
}
