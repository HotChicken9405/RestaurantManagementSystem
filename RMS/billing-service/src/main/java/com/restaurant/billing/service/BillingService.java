package com.restaurant.billing.service;

import com.restaurant.billing.entity.Bill;
import com.restaurant.billing.entity.BillItem;
import com.restaurant.billing.repository.BillRepository;
import com.restaurant.billing.repository.BillItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillItemRepository billItemRepository;

    // Get all bills
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    // Get bill by ID
    public Bill getBillById(String billId) {
        return billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + billId));
    }

    // Get bills by order ID
    public List<Bill> getBillsByOrderId(String orderId) {
        return billRepository.findByOrderId(orderId);
    }

    // Create new bill
    public Bill createBill(Bill bill) {
        // Set bill time if not provided
        if (bill.getBillTime() == null) {
            bill.setBillTime(LocalDateTime.now());
        }

        // Validate total amount
        if (bill.getTotalAmount() == null) {
            BigDecimal total = bill.getSubtotal()
                    .add(bill.getTaxAmount())
                    .subtract(bill.getDiscountAmount());
            bill.setTotalAmount(total);
        }

        return billRepository.save(bill);
    }

    // Create bill item
    public BillItem createBillItem(BillItem billItem) {
        return billItemRepository.save(billItem);
    }

    // Get bill items by bill ID
    public List<BillItem> getBillItemsByBillId(String billId) {
        return billItemRepository.findByBill_BillId(billId);
    }

    // Update payment status
    public Bill updatePaymentStatus(String billId, String status) {
        Bill bill = getBillById(billId);
        bill.setPaymentStatus(Bill.PaymentStatus.valueOf(status));
        return billRepository.save(bill);
    }

    // Get today's bills
    public List<Bill> getDailyReport() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        return billRepository.findByBillTimeBetween(startOfDay, endOfDay);
    }

    // Calculate daily totals
    public BigDecimal getDailyRevenue() {
        return getDailyReport().stream()
                .map(Bill::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}