package com.restaurant.order.repository;

import com.restaurant.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByStatus(Order.OrderStatus status);
    List<Order> findByTableId(String tableId);
}
