package com.restaurant.billing.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "bill_items")
@Data
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String billItemId;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    private String itemName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}