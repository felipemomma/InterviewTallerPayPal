package org.example.payment;

import java.math.BigDecimal;

public class Payment {
    private String id;
    private BigDecimal amount;
    private String currency;

    private PaymentStatus paymentStatus;

    public Payment(String id, BigDecimal amount, String currency, PaymentStatus paymentStatus) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentStatus getStatus() {
        return paymentStatus;
    }

    public void setStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString(){
        return "Payment {" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status=" + paymentStatus +
                '}';
    }
}
