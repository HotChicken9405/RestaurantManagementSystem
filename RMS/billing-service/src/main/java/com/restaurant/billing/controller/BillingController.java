package com.restaurant.billing.controller;

import com.restaurant.billing.entity.Bill;
import com.restaurant.billing.entity.BillItem;
import com.restaurant.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping
    public List<Bill> getAllBills() {
        return billingService.getAllBills();
    }

    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billingService.createBill(bill);
    }

    @PutMapping("/{billId}/status/{status}")
    public Bill updatePaymentStatus(
            @PathVariable String billId,
            @PathVariable String status) {
        return billingService.updatePaymentStatus(billId, status);
    }

    @PostMapping("/items")
    public BillItem createBillItem(@RequestBody BillItem billItem) {
        return billingService.createBillItem(billItem);
    }
}
