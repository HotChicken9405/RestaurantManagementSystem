package com.restaurant.menu.controller;

import com.restaurant.menu.entity.Ingredient;
import com.restaurant.menu.entity.MenuIngredient;
import com.restaurant.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "http://localhost:8081")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // ... existing endpoints ...

    // Get ingredients for a menu item
    @GetMapping("/{itemId}/ingredients")
    public List<MenuIngredient> getMenuItemIngredients(@PathVariable String itemId) {
        return menuService.getIngredientsForMenuItem(itemId);
    }

    // Update ingredient stock
    @PutMapping("/ingredients/{ingredientId}/stock")
    public Ingredient updateIngredientStock(
            @PathVariable String ingredientId,
            @RequestBody StockUpdateRequest request) {
        return menuService.updateIngredientStock(ingredientId, request.getQuantityChange());
    }

    // Get all ingredients
    @GetMapping("/ingredients")
    public List<Ingredient> getAllIngredients() {
        return menuService.getAllIngredients();
    }

    // Get ingredient by ID
    @GetMapping("/ingredients/{ingredientId}")
    public Ingredient getIngredientById(@PathVariable String ingredientId) {
        return menuService.getIngredientById(ingredientId)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }

    // DTO for stock update
    public static class StockUpdateRequest {
        private Double quantityChange;

        public Double getQuantityChange() { return quantityChange; }
        public void setQuantityChange(Double quantityChange) { this.quantityChange = quantityChange; }
    }
}