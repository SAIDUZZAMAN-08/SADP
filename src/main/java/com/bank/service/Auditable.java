package com.bank.service;

// Speculative Generality: this interface was created "just in case" auditing
// is needed in the future. No class implements it, and nothing calls it.
public interface Auditable {

    void onAccountCreated(String accountId);

    void onDeposit(String accountId, double amount);

    void onWithdraw(String accountId, double amount);

    void onTransfer(String fromAccountId, String toAccountId, double amount);

    void onAccountClosed(String accountId);
}
