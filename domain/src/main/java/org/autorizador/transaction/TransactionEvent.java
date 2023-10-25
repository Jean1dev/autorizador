package org.autorizador.transaction;

import org.autorizador.DomainEvent;

import java.time.LocalDateTime;

public class TransactionEvent implements DomainEvent {
    private String merchant;
    private double amount;
    private LocalDateTime time;

    public TransactionEvent(String merchant, double amount, LocalDateTime time) {
        this.merchant = merchant;
        this.amount = amount;
        this.time = time;
    }

    public String getMerchant() {
        return merchant;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
