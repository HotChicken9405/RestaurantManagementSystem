package com.restaurant.menu.service;

import com.restaurant.menu.entity.MenuItem;
import com.restaurant.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }

    public List<MenuItem> getAvailableMenuItems() {
        return menuRepository.findByIsAvailableTrue();
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(String itemId, MenuItem menuItem) {
        MenuItem existing = menuRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        existing.setItemName(menuItem.getItemName());
        existing.setDescription(menuItem.getDescription());
        existing.setPrice(menuItem.getPrice());
        existing.setCategory(menuItem.getCategory());
        existing.setIsAvailable(menuItem.getIsAvailable());

        return menuRepository.save(existing);
    }
}