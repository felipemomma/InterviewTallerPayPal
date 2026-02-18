package org.example.service.adapters;

import org.example.payment.Payment;
import org.example.payment.PaymentStatus;
import org.example.service.adapters.StatisticsPaymentProcessor;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class StatisticsPaymentProcessorImpl implements StatisticsPaymentProcessor {

    @Override
    public Integer totalOfSuccessfuPayments(List<Payment> paymentsList) {
        return getPaymentsByStatus(paymentsList,PaymentStatus.SUCCESS).size();
    }
    @Override
    public BigDecimal getTotalSuccessfulAmount(List<Payment> paymentsList) {
        return paymentsList.stream()
                .filter(p -> p.getStatus() == PaymentStatus.SUCCESS)
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    @Override
    public double averageOfSuccessfulPayments(List<Payment> paymentsList) {
        return (double)totalOfSuccessfuPayments(paymentsList)/(double)paymentsList.size();
    }
    @Override
    public BigDecimal averageSuccessfulPaymentsAmount(List<Payment> paymentsList) {
        List<BigDecimal> amounts = paymentsList.stream()
                .filter(p -> p.getStatus() == PaymentStatus.SUCCESS)
                .map(Payment::getAmount)
                .toList();
        if (amounts.isEmpty()) return BigDecimal.ZERO;

        BigDecimal total = amounts.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total.divide(BigDecimal.valueOf(amounts.size()), 2, BigDecimal.ROUND_HALF_UP);
    }
    @Override
    public List<Payment> paymentsSortedByAmountDesc(List<Payment> paymentsList) {
        return paymentsList.stream()
                .sorted(Comparator.comparing(Payment::getAmount).reversed())
                .collect(Collectors.toList());
    }
    @Override
    public void processPaymentsInParallel(List<Payment> paymentsList) {
        List<CompletableFuture<Void>> futuresPaymentProcess = paymentsList.stream()
                .map(p -> CompletableFuture.runAsync(() -> {
                    System.out.println("Processing paymentId=" + p.getId() + " on " + Thread.currentThread().getName());

                    try {
                        //process payment
                        Thread.sleep(200);
                        p.setStatus(PaymentStatus.SUCCESS);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }))
                .toList();

        CompletableFuture.allOf(futuresPaymentProcess.toArray(new CompletableFuture[0])).join();
    }

    //boilerplate
    private List<Payment> getPaymentsByStatus(List<Payment> paymentsList, PaymentStatus status) {
        return paymentsList.stream()
                .filter(p -> p.getStatus() == status)
                .collect(Collectors.toList());
    }
}
