package com.bank.service;

import com.bank.model.Transaction;
import com.bank.model.TransactionType;

import java.util.List;

// Feature Envy: this class is obsessed with Transaction's data.
// It reaches into Transaction for every field to do work that really
// belongs inside Transaction or Account itself.
public class TransactionAnalyzer {

    public double calculateTotalDeposits(List<Transaction> transactions) {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.DEPOSIT) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalWithdrawals(List<Transaction> transactions) {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.WITHDRAWAL) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalInterestEarned(List<Transaction> transactions) {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.INTEREST) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public Transaction findLargestTransaction(List<Transaction> transactions) {
        Transaction largest = null;
        for (Transaction t : transactions) {
            if (largest == null || t.getAmount() > largest.getAmount()) {
                largest = t;
            }
        }
        return largest;
    }

    public void printTransactionReport(List<Transaction> transactions) {
        System.out.println("=== Transaction Report ===");
        for (Transaction t : transactions) {
            System.out.println("ID      : " + t.getTransactionId());
            System.out.println("Account : " + t.getAccountId());
            System.out.println("Type    : " + t.getType());
            System.out.println("Amount  : " + t.getAmount());
            System.out.println("Balance : " + t.getBalanceAfter());
            System.out.println("Note    : " + t.getNote());
            System.out.println("Time    : " + t.getTimestamp());
            System.out.println("--------------------------");
        }
    }
}
