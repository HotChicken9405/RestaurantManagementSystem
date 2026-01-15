package com.restaurant.billing.controller;


import com.restaurant.billing.entity.Bill;
import com.restaurant.billing.entity.BillItem;
import com.restaurant.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "http://localhost:8081")
public class BillingController {

    @Autowired
    private BillingService billingService;

    // Get all bills
    @GetMapping
    public List<Bill> getAllBills() {
        return billingService.getAllBills();
    }

    // Get bill by ID
    @GetMapping("/{billId}")
    public Bill getBillById(@PathVariable String billId) {
        return billingService.getBillById(billId);
    }

    // Get bills by order ID
    @GetMapping("/order/{orderId}")
    public List<Bill> getBillsByOrderId(@PathVariable String orderId) {
        return billingService.getBillsByOrderId(orderId);
    }

    // Create new bill
    @PostMapping
    public Bill createBill(@RequestBody Bill bill) {
        return billingService.createBill(bill);
    }

    // Create bill item
    @PostMapping("/items")
    public BillItem createBillItem(@RequestBody BillItem billItem) {
        return billingService.createBillItem(billItem);
    }

    // Get bill items by bill ID
    @GetMapping("/{billId}/items")
    public List<BillItem> getBillItems(@PathVariable String billId) {
        return billingService.getBillItemsByBillId(billId);
    }

    // Update payment status
    @PutMapping("/{billId}/status/{status}")
    public Bill updatePaymentStatus(
            @PathVariable String billId,
            @PathVariable String status) {
        return billingService.updatePaymentStatus(billId, status);
    }

    // Get daily report
    @GetMapping("/report/daily")
    public List<Bill> getDailyReport() {
        return billingService.getDailyReport();
    }
}