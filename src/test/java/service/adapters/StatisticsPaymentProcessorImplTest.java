package org.example.service.adapters;

import org.example.payment.Payment;
import org.example.payment.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsPaymentProcessorImplTest {

    private StatisticsPaymentProcessorImpl processor;
    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        processor = new StatisticsPaymentProcessorImpl();

        payments = List.of(
                new Payment("p1", new BigDecimal("100.00"), "USD", PaymentStatus.SUCCESS),
                new Payment("p2", new BigDecimal("50.00"), "USD", PaymentStatus.FAILED),
                new Payment("p3", new BigDecimal("200.00"), "USD", PaymentStatus.SUCCESS),
                new Payment("p4", new BigDecimal("10.00"), "USD", PaymentStatus.PENDING)
        );
    }

    @Test
    void shouldCountSuccessfulPayments() {
        Integer result = processor.totalOfSuccessfuPayments(payments);
        assertEquals(2, result);
    }

    @Test
    void shouldCalculateTotalSuccessfulAmount() {
        BigDecimal total = processor.getTotalSuccessfulAmount(payments);
        assertEquals(new BigDecimal("300.00"), total);
    }

    @Test
    void shouldCalculateAverageOfSuccessfulPaymentsRatio() {
        double avg = processor.averageOfSuccessfulPayments(payments);
        assertEquals(0.5, avg);
    }

    @Test
    void shouldCalculateAverageSuccessfulPaymentsAmount() {
        BigDecimal avgAmount = processor.averageSuccessfulPaymentsAmount(payments);
        assertEquals(new BigDecimal("150.00"), avgAmount);
    }

    @Test
    void shouldReturnZeroAverageWhenNoSuccessPayments() {
        List<Payment> noSuccess = List.of(
                new Payment("p1", new BigDecimal("10.00"), "USD", PaymentStatus.FAILED)
        );

        BigDecimal avg = processor.averageSuccessfulPaymentsAmount(noSuccess);
        assertEquals(BigDecimal.ZERO, avg);
    }

    @Test
    void shouldSortPaymentsByAmountDesc() {
        List<Payment> sorted = processor.paymentsSortedByAmountDesc(payments);

        assertEquals("p3", sorted.get(0).getId());
        assertEquals("p1", sorted.get(1).getId());
        assertEquals("p2", sorted.get(2).getId());
        assertEquals("p4", sorted.get(3).getId());
    }

    @Test
    void shouldProcessPaymentsInParallelAndSetStatusToSuccess() {
        List<Payment> paymentList = payments.stream()
                .map(p -> new Payment(p.getId(), p.getAmount(), p.getCurrency(), p.getStatus()))
                .toList();

        processor.processPaymentsInParallel(paymentList);

        assertTrue(paymentList.stream().allMatch(p -> p.getStatus() == PaymentStatus.SUCCESS));
    }
}
