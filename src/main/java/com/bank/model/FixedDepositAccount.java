package com.bank.model;

public class FixedDepositAccount extends Account {

    private final int termMonths;
    private final double interestRate;
    private boolean matured;

    public FixedDepositAccount(String accountId, String holderName,
                               double depositAmount, double interestRate, int termMonths) {
        super(accountId, holderName, depositAmount);
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.matured = false;
    }

    @Override
    public String getAccountType() {
        return "FIXED_DEPOSIT";
    }

    public int getTermMonths() {
        return termMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public boolean isMatured() {
        return matured;
    }

    public void markMatured() {
        this.matured = true;
    }

    // Refused Bequest: FixedDepositAccount inherits withdraw() and deposit()
    // from Account but cannot support them — it refuses the parent's contract.
    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException(
                "Withdrawals are not allowed on a fixed deposit account before maturity.");
    }

    @Override
    public void deposit(double amount) {
        throw new UnsupportedOperationException(
                "Additional deposits are not allowed on a fixed deposit account.");
    }

    public double calculateMaturityAmount() {
        return balance + (balance * (interestRate / 100.0) * termMonths);
    }
}
