package com.restaurant.billing.repository;

import com.restaurant.billing.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {

    List<Bill> findByOrderId(String orderId);

    List<Bill> findByPaymentStatus(Bill.PaymentStatus status);

    List<Bill> findByBillTimeBetween(LocalDateTime start, LocalDateTime end);
}