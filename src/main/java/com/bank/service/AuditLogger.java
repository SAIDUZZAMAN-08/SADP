package com.bank.service;

// Speculative Generality: implements the Auditable interface that is never
// registered or injected anywhere in the application.
public class AuditLogger implements Auditable {

    @Override
    public void onAccountCreated(String accountId) {
        System.out.println("[AUDIT] Account created: " + accountId);
    }

    @Override
    public void onDeposit(String accountId, double amount) {
        System.out.println("[AUDIT] Deposit on " + accountId + " amount=" + amount);
    }

    @Override
    public void onWithdraw(String accountId, double amount) {
        System.out.println("[AUDIT] Withdrawal on " + accountId + " amount=" + amount);
    }

    @Override
    public void onTransfer(String fromAccountId, String toAccountId, double amount) {
        System.out.println("[AUDIT] Transfer from " + fromAccountId
                + " to " + toAccountId + " amount=" + amount);
    }

    @Override
    public void onAccountClosed(String accountId) {
        System.out.println("[AUDIT] Account closed: " + accountId);
    }
}
