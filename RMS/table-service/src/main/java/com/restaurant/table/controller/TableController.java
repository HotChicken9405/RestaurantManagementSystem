package com.restaurant.table.controller;

import com.restaurant.table.entity.Reservation;
import com.restaurant.table.entity.RestaurantTable;
import com.restaurant.table.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin(origins = "http://localhost:8081")  // ← ADD THIS!
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping
    public List<RestaurantTable> getAllTables() {
        return tableService.getAllTables();
    }

    @GetMapping("/available")
    public List<RestaurantTable> getAvailableTables() {
        return tableService.getAvailableTables();
    }

    @PutMapping("/{tableId}/status/{status}")
    public RestaurantTable updateTableStatus(
            @PathVariable String tableId,
            @PathVariable String status) {
        return tableService.updateTableStatus(tableId, status);
    }

    @PostMapping("/reservations")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return tableService.createReservation(reservation);
    }

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return tableService.getAllReservations();
    }
}