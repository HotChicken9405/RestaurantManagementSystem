package com.restaurant.menu.repository;


import com.restaurant.menu.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    // Basic CRUD operations are inherited
}
