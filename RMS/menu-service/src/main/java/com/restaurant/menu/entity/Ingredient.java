package com.restaurant.menu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ingredients")
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ingredientId;

    private String ingredientName;
    private String unit;
    private Double currentQuantity;
    private Double minQuantity;
}
