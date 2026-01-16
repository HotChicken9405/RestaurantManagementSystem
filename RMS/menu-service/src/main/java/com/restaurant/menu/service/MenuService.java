package com.restaurant.menu.service;

import com.restaurant.menu.entity.Ingredient;
import com.restaurant.menu.entity.MenuIngredient;
import com.restaurant.menu.entity.MenuItem;
import com.restaurant.menu.repository.IngredientRepository;
import com.restaurant.menu.repository.MenuIngredientRepository;
import com.restaurant.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private MenuIngredientRepository menuIngredientRepository;

    // ... existing methods ...

    // Get ingredients for a menu item
    public List<MenuIngredient> getIngredientsForMenuItem(String itemId) {
        return menuIngredientRepository.findByMenuItem_ItemId(itemId);
    }

    // Update ingredient stock
    public Ingredient updateIngredientStock(String ingredientId, Double quantityChange) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientId);

        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();
            Double newQuantity = ingredient.getCurrentQuantity() + quantityChange;

            // Ensure quantity doesn't go negative
            if (newQuantity < 0) newQuantity = 0.0;

            ingredient.setCurrentQuantity(newQuantity);
            return ingredientRepository.save(ingredient);
        }

        throw new RuntimeException("Ingredient not found with id: " + ingredientId);
    }

    // Get all ingredients
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    // Get ingredient by ID
    public Optional<Ingredient> getIngredientById(String ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }
}