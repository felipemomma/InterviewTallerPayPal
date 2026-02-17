package org.example;

import java.util.ArrayList;
import java.util.List;

public class PaymentProcessor {
    private List<Payment> paymentList;

    public PaymentProcessor(List<Payment> paymentList){
        this.paymentList = paymentList;
    }
    public void addPayment(Payment payment) {
        this.paymentList.add(payment);
    }
    public List<Payment> retrievePaymentList() {
        return this.paymentList;
    }
    public List<Payment> retrievePaymentListByStatus(Status status) {
        List<Payment> statusList = new ArrayList<>();
        for(Payment payment : paymentList) {
            if(payment.getStatus().equals(status)) {
                statusList.add(payment);
            }
        }

        return statusList;
    }

    public Integer totalOfPayments() {
        return this.paymentList.size();
    }

    public Integer totalOfSuccessfuPayments() {
        return retrievePaymentListByStatus(Status.SUCCESS).size();
    }

    public double averageOfSuccessfulPayments() {
        return (double)totalOfSuccessfuPayments()/(double)totalOfPayments();
    }
}
