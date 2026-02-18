package org.example.service.adapters;

import org.example.payment.Payment;
import org.example.payment.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentProcessor {
    public void addPayment(Payment payment);
    public List<Payment> retrievePaymentList();
    public List<Payment> retrievePaymentListByStatus(PaymentStatus status);
    public Integer totalOfPayments();
}
