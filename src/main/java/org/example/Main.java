package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Payment> paymentList = new ArrayList<>();
        Payment payment1 = new Payment("1",1.00,"USD",Status.PENDING);
        Payment payment2 = new Payment("1",12.00,"USD",Status.SUCCESS);
        Payment payment3 = new Payment("1",14.00,"USD",Status.FAILED);
        Payment payment4 = new Payment("1",1.00,"USD",Status.SUCCESS);
        Payment payment5 = new Payment("1",51.00,"USD",Status.PENDING);
        paymentList.add(payment1);
        paymentList.add(payment2);
        paymentList.add(payment3);
        paymentList.add(payment4);
        paymentList.add(payment5);

        PaymentProcessor paymentProcessor = new PaymentProcessor(paymentList);
        double avg = paymentProcessor.averageOfSuccessfulPayments();
        System.out.println(avg);
    }
}