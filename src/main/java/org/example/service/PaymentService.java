package org.example.service;

import org.example.payment.Payment;
import org.example.service.adapters.PaymentProcessor;
import org.example.service.adapters.StatisticsPaymentProcessor;

import java.util.List;

public class PaymentService {
    public void run(List<Payment> paymentsList, PaymentProcessor processor, StatisticsPaymentProcessor statisticsPaymentProcessor) {
        System.out.println("All payments:");
        processor.retrievePaymentList().forEach(System.out::println);

        System.out.println("Stats:");
        System.out.println("Total payments = " + processor.totalOfPayments());
        System.out.println("Total SUCCESS amount = " + statisticsPaymentProcessor.getTotalSuccessfulAmount(paymentsList));
        System.out.println("Average SUCCESS amount = " + statisticsPaymentProcessor.averageSuccessfulPaymentsAmount(paymentsList));
        System.out.println("Sorted:");

        statisticsPaymentProcessor.paymentsSortedByAmountDesc(paymentsList).forEach(System.out::println);

        System.out.println("Parallel processing...");
        statisticsPaymentProcessor.processPaymentsInParallel(paymentsList);
    }
}
