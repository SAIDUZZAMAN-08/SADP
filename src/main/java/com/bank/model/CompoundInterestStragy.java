package com.bank.model;


public class CompoundInterestStrategy implements InterestStrategy {

    @Override
    public double calculateInterest(
            double balance,
            double interestRate,
            int months) {

        return balance *
                Math.pow(1 + interestRate / 100.0, months)
                - balance;
    }
}