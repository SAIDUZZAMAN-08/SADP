package com.bank.model;

import com.bank.exception.InsufficientFundsException;
import com.bank.util.Validator;

public class CurrentAccount extends Account {

    private final double overdraftLimit;

    public CurrentAccount(String accountId, String holderName, double openingBalance, double overdraftLimit) {
        super(accountId, holderName, openingBalance);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String getAccountType() {
        return "CURRENT";
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    // Duplicate Code: nearly identical to SavingsAccount.printSummary()
    public void printSummary() {
        System.out.println("Account ID   : " + getAccountId());
        System.out.println("Holder Name  : " + getHolderName());
        System.out.println("Account Type : " + getAccountType());
        System.out.println("Balance      : " + String.format("%.2f", getBalance()));
        System.out.println("Transactions : " + getTransactionHistory().size());
    }

    @Override
    public void withdraw(double amount) {
        Validator.validateAmount(amount);
        if (amount > balance + overdraftLimit) {
            throw new InsufficientFundsException(
                    "Cannot withdraw " + amount + " (balance " + balance
                            + " + overdraft " + overdraftLimit + ")");
        }
        balance -= amount;
        recordEntry(TransactionType.WITHDRAWAL, amount, "Withdrawal");
    }
}
