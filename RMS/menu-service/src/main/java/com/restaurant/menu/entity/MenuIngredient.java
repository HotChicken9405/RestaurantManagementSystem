package com.restaurant.menu.entity;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "menu_item_ingredients")
@Data
public class MenuIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String menuIngredientId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private Double quantityRequired;
}
