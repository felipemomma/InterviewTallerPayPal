package org.example;

import org.example.payment.Payment;
import org.example.service.*;
import org.example.payment.PaymentStatus;
import org.example.service.adapters.PaymentProcessor;
import org.example.service.adapters.PaymentProcessorImpl;
import org.example.service.adapters.StatisticsPaymentProcessor;
import org.example.service.adapters.StatisticsPaymentProcessorImpl;

import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Payment> paymentList = new ArrayList<>();
        Payment payment1 = new Payment("1",new BigDecimal("100.50"),"USD", PaymentStatus.PENDING);
        Payment payment2 = new Payment("5",new BigDecimal("10.00"),"USD", PaymentStatus.SUCCESS);
        Payment payment3 = new Payment("3",new BigDecimal("23.47"),"USD", PaymentStatus.FAILED);
        Payment payment4 = new Payment("4",new BigDecimal("50.10"),"USD", PaymentStatus.SUCCESS);
        Payment payment5 = new Payment("2",new BigDecimal("1.56"),"USD", PaymentStatus.PENDING);

        paymentList.add(payment1);
        paymentList.add(payment2);
        paymentList.add(payment3);
        paymentList.add(payment4);
        paymentList.add(payment5);

        PaymentService service = new PaymentService();
        PaymentProcessor paymentProcessor = new PaymentProcessorImpl(paymentList);
        StatisticsPaymentProcessor statisticsPaymentProcessor = new StatisticsPaymentProcessorImpl();
        service.run(paymentList,paymentProcessor,statisticsPaymentProcessor);
    }
}