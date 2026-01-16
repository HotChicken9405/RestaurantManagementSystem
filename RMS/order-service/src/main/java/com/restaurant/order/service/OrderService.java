package com.restaurant.order.service;

import com.restaurant.order.entity.Order;
import com.restaurant.order.entity.OrderItem;
import com.restaurant.order.repository.OrderRepository;
import com.restaurant.order.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get orders by status
    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    // Get orders for waiter (READY, BILLED)
    public List<Order> getOrdersForWaiter() {
        return orderRepository.findByStatusIn(Arrays.asList(
                Order.OrderStatus.READY,
                Order.OrderStatus.BILLED
        ));
    }

    // Get orders for cashier (READY, SERVED)
    public List<Order> getOrdersForCashier() {
        return orderRepository.findByStatusIn(Arrays.asList(
                Order.OrderStatus.READY,
                Order.OrderStatus.SERVED
        ));
    }

    // Create order
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Update order status
    public Order updateOrderStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        try {
            Order.OrderStatus newStatus = Order.OrderStatus.valueOf(status);
            order.setStatus(newStatus);
            return orderRepository.save(order);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid order status: " + status);
        }
    }

    // Complete order (when both served and billed)
    public Order completeOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(Order.OrderStatus.COMPLETED);
        return orderRepository.save(order);


    }

    // Create order item
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }


    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        return orderItemRepository.findByOrder_OrderId(orderId);
    }
}