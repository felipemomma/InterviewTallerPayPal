package org.example.service.adapters;

import org.example.payment.Payment;
import org.example.payment.PaymentStatus;
import org.example.service.adapters.PaymentProcessor;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentProcessorImpl implements PaymentProcessor {
    private List<Payment> paymentsList;

    public PaymentProcessorImpl(List<Payment> paymentsList){
        this.paymentsList = paymentsList;
    }
    @Override
    public void addPayment(Payment payment) {
        this.paymentsList.add(payment);
    }
    @Override
    public List<Payment> retrievePaymentList() {
        return this.paymentsList;
    }
    @Override
    public List<Payment> retrievePaymentListByStatus(PaymentStatus status) {
        return getPaymentsByStatus(status);
    }
    @Override
    public Integer totalOfPayments() {
        return this.paymentsList.size();
    }

    //boilerplate
    private List<Payment> getPaymentsByStatus(PaymentStatus status) {
        return paymentsList.stream()
                .filter(p -> p.getStatus() == status)
                .collect(Collectors.toList());
    }
}
