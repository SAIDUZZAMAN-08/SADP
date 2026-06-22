package com.bank.model;

public class SimpleInterestStrategy implements InterestStrategy {

    @Override
    public double calculateInterest(
            double balance,
            double interestRate,
            int months) {

        return balance * (interestRate / 100.0) * months;
    }
}