package com.restaurant.order.controller;

import com.restaurant.order.entity.Order;
import com.restaurant.order.entity.OrderItem;
import com.restaurant.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:8081")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get orders by status
    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable String status) {
        return orderService.getOrdersByStatus(Order.OrderStatus.valueOf(status));
    }

    // Get orders for waiter (READY, BILLED)
    @GetMapping("/waiter")
    public List<Order> getOrdersForWaiter() {
        return orderService.getOrdersForWaiter();
    }

    // Get orders for cashier (READY, SERVED)
    @GetMapping("/cashier")
    public List<Order> getOrdersForCashier() {
        return orderService.getOrdersForCashier();
    }

    // Create order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // Update order status
    @PutMapping("/{orderId}/status/{status}")
    public Order updateOrderStatus(
            @PathVariable String orderId,
            @PathVariable String status) {
        return orderService.updateOrderStatus(orderId, status);
    }

    // Complete order (mark as COMPLETED)
    @PutMapping("/{orderId}/complete")
    public Order completeOrder(@PathVariable String orderId) {
        return orderService.completeOrder(orderId);
    }

    // Create order item
    @PostMapping("/items")
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderService.createOrderItem(orderItem);
    }


    @GetMapping("/{orderId}/items")
    public List<OrderItem> getOrderItems(@PathVariable String orderId) {
        return orderService.getOrderItemsByOrderId(orderId);
    }
}