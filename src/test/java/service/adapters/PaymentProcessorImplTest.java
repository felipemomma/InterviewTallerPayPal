package org.example.service.adapters;

import org.example.payment.Payment;
import org.example.payment.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentProcessorImplTest {

    private PaymentProcessorImpl processor;
    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>(List.of(
                new Payment("p1", new BigDecimal("100.00"), "USD", PaymentStatus.SUCCESS),
                new Payment("p2", new BigDecimal("50.00"), "USD", PaymentStatus.FAILED),
                new Payment("p3", new BigDecimal("200.00"), "USD", PaymentStatus.SUCCESS)
        ));

        processor = new PaymentProcessorImpl(payments);
    }

    @Test
    void shouldAddPayment() {
        Payment newPayment = new Payment("p4", new BigDecimal("10.00"), "USD", PaymentStatus.PENDING);

        processor.addPayment(newPayment);

        assertEquals(4, processor.totalOfPayments());
        assertTrue(processor.retrievePaymentList().contains(newPayment));
    }

    @Test
    void shouldRetrieveAllPayments() {
        List<Payment> result = processor.retrievePaymentList();

        assertEquals(3, result.size());
        assertEquals("p1", result.get(0).getId());
    }

    @Test
    void shouldRetrievePaymentsByStatus() {
        List<Payment> successPayments = processor.retrievePaymentListByStatus(PaymentStatus.SUCCESS);

        assertEquals(2, successPayments.size());
        assertTrue(successPayments.stream().allMatch(p -> p.getStatus() == PaymentStatus.SUCCESS));
    }

    @Test
    void shouldReturnTotalOfPayments() {
        assertEquals(3, processor.totalOfPayments());
    }

    @Test
    void shouldReturnEmptyListWhenNoPaymentsWithStatus() {
        List<Payment> pendingPayments = processor.retrievePaymentListByStatus(PaymentStatus.PENDING);

        assertTrue(pendingPayments.isEmpty());
    }

    @Test
    void shouldHandleEmptyPaymentList() {
        PaymentProcessorImpl emptyProcessor = new PaymentProcessorImpl(new ArrayList<>());

        assertEquals(0, emptyProcessor.totalOfPayments());
        assertTrue(emptyProcessor.retrievePaymentList().isEmpty());
    }
}
