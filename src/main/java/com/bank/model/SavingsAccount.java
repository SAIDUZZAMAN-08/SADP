package com.bank.model;

public class SavingsAccount extends Account {

    private final double interestRate;
    private InterestStrategy interestStrategy;

    public SavingsAccount(
            String accountId,
            String holderName,
            double openingBalance,
            double interestRate,
            InterestStrategy strategy) {

        super(accountId, holderName, openingBalance);

        this.interestRate = interestRate;
        this.interestStrategy = strategy;
    }

    public void setInterestStrategy(
            InterestStrategy strategy) {

        this.interestStrategy = strategy;
    }

    public double calculateInterest(int months) {

        if(months < 0)
            throw new IllegalArgumentException(
                    "Months cannot be negative");

        return interestStrategy.calculateInterest(
                balance,
                interestRate,
                months);
    }

    public void applyInterest(int months) {

        double interest =
                calculateInterest(months);

        if(interest > 0) {
            recordInterest(interest);
        }
    }
}