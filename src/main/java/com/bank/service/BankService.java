package com.bank.service;

import com.bank.exception.AccountNotFoundException;
import com.bank.exception.DuplicateAccountException;
import com.bank.model.Account;
import com.bank.model.CurrentAccount;
import com.bank.model.SavingsAccount;
import com.bank.model.Transaction;
import com.bank.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {

    private final Map<String, Account> accounts = new HashMap<>();
    private final String bankName;

    public BankService(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public SavingsAccount createSavingsAccount(String accountId, String holderName,
                                               double openingBalance, double interestRate) {
        ensureUnique(accountId);
        SavingsAccount account = new SavingsAccount(accountId, holderName, openingBalance, interestRate);
        accounts.putIfAbsent(accountId, account);
        return account;
    }

    public CurrentAccount createCurrentAccount(String accountId, String holderName,
                                               double openingBalance, double overdraftLimit) {
        ensureUnique(accountId);
        CurrentAccount account = new CurrentAccount(accountId, holderName, openingBalance, overdraftLimit);
        accounts.putIfAbsent(accountId, account);
        return account;
    }

    private void ensureUnique(String accountId) {
        Validator.validateAccountId(accountId);
        if (accounts.containsKey(accountId.trim())) {
            throw new DuplicateAccountException("Account already exists: " + accountId);
        }
    }

    public Account getAccount(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new AccountNotFoundException("No account with id: " + accountId);
        }
        return account;
    }

    public void deposit(String accountId, double amount) {
        getAccount(accountId).deposit(amount);
    }

    public void withdraw(String accountId, double amount) {
        getAccount(accountId).withdraw(amount);
    }

    public double checkBalance(String accountId) {
        return getAccount(accountId).getBalance();
    }

    public List<Transaction> getTransactionHistory(String accountId) {
        return new ArrayList<>(getAccount(accountId).getTransactionHistory());
    }

    // Dead Code: this method is never called anywhere in the application.
    private String formatAccountReport(Account account) {
        StringBuilder sb = new StringBuilder();
        sb.append("==== Account Report ====\n");
        sb.append("Bank     : ").append(bankName).append("\n");
        sb.append("Account  : ").append(account.getAccountId()).append("\n");
        sb.append("Holder   : ").append(account.getHolderName()).append("\n");
        sb.append("Type     : ").append(account.getAccountType()).append("\n");
        sb.append("Balance  : ").append(String.format("%.2f", account.getBalance())).append("\n");
        sb.append("Txn Count: ").append(account.getTransactionHistory().size()).append("\n");
        return sb.toString();
    }

    // Dead Code: intended as a bulk close utility, but never wired up or called.
    private void closeAllAccounts() {
        accounts.clear();
    }
}
