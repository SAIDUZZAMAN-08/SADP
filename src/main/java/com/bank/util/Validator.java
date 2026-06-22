package com.bank.util;

import com.bank.exception.InvalidAmountException;

public final class Validator {

    private Validator() {
    }

    public static void validateAmount(double amount) {
        if (amount < 0) {
            throw new InvalidAmountException("Amount cannot be negative: " + amount);
        }
    }

    public static void validateAccountId(String accountId) {
        if (accountId == null || accountId.trim().isEmpty()) {
            throw new IllegalArgumentException("Account ID cannot be null or empty");
        }
    }

    public static void validateHolderName(String holderName) {
        if (holderName == null || holderName.trim().length() < 2) {
            throw new IllegalArgumentException("Holder name must be at least 2 characters");
        }
    }

    public static boolean isPositive(double amount) {
        return amount > 0;
    }
}
