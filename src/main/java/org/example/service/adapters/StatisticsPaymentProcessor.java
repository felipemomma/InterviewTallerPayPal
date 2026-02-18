package org.example.service.adapters;

import org.example.payment.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface StatisticsPaymentProcessor {
    public BigDecimal getTotalSuccessfulAmount(List<Payment> paymentsList);
    public Integer totalOfSuccessfuPayments(List<Payment> paymentsList);
    public double averageOfSuccessfulPayments(List<Payment> paymentsList);
    public BigDecimal averageSuccessfulPaymentsAmount(List<Payment> paymentsList);
    public List<Payment> paymentsSortedByAmountDesc(List<Payment> paymentsList);
    public void processPaymentsInParallel(List<Payment> paymentsList);
}
