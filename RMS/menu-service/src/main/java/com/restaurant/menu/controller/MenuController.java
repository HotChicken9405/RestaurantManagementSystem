package com.restaurant.menu.controller;

import com.restaurant.menu.entity.MenuItem;
import com.restaurant.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuService.getAllMenuItems();
    }

    @GetMapping("/available")
    public List<MenuItem> getAvailableMenuItems() {
        return menuService.getAvailableMenuItems();
    }

    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuService.createMenuItem(menuItem);
    }

    @PutMapping("/{itemId}")
    public MenuItem updateMenuItem(
            @PathVariable String itemId,
            @RequestBody MenuItem menuItem) {
        return menuService.updateMenuItem(itemId, menuItem);
    }
}