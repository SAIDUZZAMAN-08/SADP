package com.bank.model;

import java.time.LocalDateTime;

public class Transaction {

    private final String transactionId;
    private final String accountId;
    private final TransactionType type;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime timestamp;
    private final String note;

    public Transaction(String transactionId,
                       String accountId,
                       TransactionType type,
                       double amount,
                       double balanceAfter,
                       String note) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
        this.note = note;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | amount=%.2f | balance=%.2f | %s",
                timestamp, transactionId, type, amount, balanceAfter, note == null ? "" : note);
    }
}
