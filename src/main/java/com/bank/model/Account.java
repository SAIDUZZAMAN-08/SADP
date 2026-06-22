package com.bank.model;

import com.bank.exception.InsufficientFundsException;
import com.bank.util.IdGenerator;
import com.bank.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {

    private final String accountId;
    private final String holderName;
    protected double balance;
    private final List<Transaction> transactions;

    protected Account(String accountId, String holderName, double openingBalance) {
        Validator.validateAccountId(accountId);
        Validator.validateHolderName(holderName);
        Validator.validateAmount(openingBalance);
        this.accountId = accountId;
        this.holderName = holderName;
        this.balance = openingBalance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public abstract String getAccountType();

    public void deposit(double amount) {
        Validator.validateAmount(amount);
        balance += amount;
        record(TransactionType.DEPOSIT, amount, "Deposit");
    }

    public void withdraw(double amount) {
        Validator.validateAmount(amount);
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Cannot withdraw " + amount + " from balance " + balance);
        }
        balance -= amount;
        record(TransactionType.WITHDRAWAL, amount, "Withdrawal");
    }

    public void recordInterest(double amount) {
        balance += amount;
        record(TransactionType.INTEREST, amount, "Interest credited");
    }

    protected void recordEntry(TransactionType type, double amount, String note) {
        record(type, amount, note);
    }

    private void record(TransactionType type, double amount, String note) {
        Transaction txn = new Transaction(
                IdGenerator.nextTransactionId(),
                accountId,
                type,
                amount,
                balance,
                note);
        transactions.add(txn);
    }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s, holder=%s, balance=%.2f]",
                getAccountType(), accountId, holderName, balance);
    }
}
