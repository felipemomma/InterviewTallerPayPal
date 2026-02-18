package org.example.service;

import org.example.payment.Payment;
import org.example.payment.PaymentStatus;
import org.example.service.adapters.PaymentProcessor;
import org.example.service.adapters.StatisticsPaymentProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentProcessor paymentProcessor;

    @Mock
    private StatisticsPaymentProcessor statisticsProcessor;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void shouldCallProcessorsCorrectly() {
        List<Payment> payments = List.of(
                new Payment("p1", new BigDecimal("100.00"), "USD", PaymentStatus.SUCCESS),
                new Payment("p2", new BigDecimal("50.00"), "USD", PaymentStatus.FAILED)
        );

        when(paymentProcessor.retrievePaymentList()).thenReturn(payments);
        when(paymentProcessor.totalOfPayments()).thenReturn(2);
        when(statisticsProcessor.getTotalSuccessfulAmount(payments)).thenReturn(new BigDecimal("100.00"));
        when(statisticsProcessor.averageSuccessfulPaymentsAmount(payments)).thenReturn(new BigDecimal("100.00"));
        when(statisticsProcessor.paymentsSortedByAmountDesc(payments)).thenReturn(payments);

        paymentService.run(payments, paymentProcessor, statisticsProcessor);

        verify(paymentProcessor).retrievePaymentList();
        verify(paymentProcessor).totalOfPayments();
        verify(statisticsProcessor).getTotalSuccessfulAmount(payments);
        verify(statisticsProcessor).averageSuccessfulPaymentsAmount(payments);
        verify(statisticsProcessor).paymentsSortedByAmountDesc(payments);
        verify(statisticsProcessor).processPaymentsInParallel(payments);
    }
}
