package com.restaurant.billing.service;

import com.restaurant.billing.entity.Bill;
import com.restaurant.billing.entity.BillItem;
import com.restaurant.billing.repository.BillItemRepository;
import com.restaurant.billing.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillItemRepository billItemRepository;

    public Bill createBill(Bill bill) {
        // Calculate total if not provided
        if (bill.getTotalAmount() == null) {
            BigDecimal total = bill.getSubtotal()
                    .add(bill.getTaxAmount())
                    .subtract(bill.getDiscountAmount());
            bill.setTotalAmount(total);
        }
        return billRepository.save(bill);
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill updatePaymentStatus(String billId, String status) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
        bill.setPaymentStatus(Bill.PaymentStatus.valueOf(status));
        return billRepository.save(bill);
    }

    public BillItem createBillItem(BillItem billItem) {
        return billItemRepository.save(billItem);
    }
}
