package com.bank.model;

public interface InterestStrategy {
    double calculateInterest(double balance,
                             double interestRate,
                             int months);
}
