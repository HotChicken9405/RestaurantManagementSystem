package com.restaurant.billing.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String billId;

    private String orderId;
    private String cashierId;

    private BigDecimal subtotal;
    private BigDecimal taxAmount = BigDecimal.ZERO;
    private BigDecimal discountAmount = BigDecimal.ZERO;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod = PaymentMethod.CASH;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    private LocalDateTime billTime = LocalDateTime.now();

    public enum PaymentMethod {
        CASH, CARD
    }

    public enum PaymentStatus {
        PENDING, PAID, FAILED, REFUNDED
    }
}