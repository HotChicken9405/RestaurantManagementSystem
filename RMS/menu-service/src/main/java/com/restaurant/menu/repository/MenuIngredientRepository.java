package com.restaurant.menu.repository;

import com.restaurant.menu.entity.MenuIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MenuIngredientRepository extends JpaRepository<MenuIngredient, String> {
    List<MenuIngredient> findByMenuItem_ItemId(String itemId);
    List<MenuIngredient> findByIngredient_IngredientId(String ingredientId);
}
