package com.restaurant.order.controller;

import com.restaurant.order.entity.Order;
import com.restaurant.order.entity.OrderItem;
import com.restaurant.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/pending")
    public List<Order> getPendingOrders() {
        return orderService.getPendingOrders();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{orderId}/status/{status}")
    public Order updateOrderStatus(
            @PathVariable String orderId,
            @PathVariable String status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    @PostMapping("/items")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderService.createOrderItem(orderItem);
    }
}
