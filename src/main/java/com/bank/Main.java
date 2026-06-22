package com.bank;

import com.bank.exception.BankingException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.BankService;
import com.bank.util.IdGenerator;

import java.util.Scanner;

public class Main {

    private final BankService bank;
    private final Scanner scanner;

    public Main(BankService bank, Scanner scanner) {
        this.bank = bank;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        BankService bank = new BankService("Lab Bank");
        Scanner scanner = new Scanner(System.in);
        new Main(bank, scanner).run();
    }

    public void run() {
        System.out.println("Welcome to " + bank.getBankName());
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        createSavings();
                        break;
                    case "2":
                        createCurrent();
                        break;
                    case "3":
                        deposit();
                        break;
                    case "4":
                        withdraw();
                        break;
                    case "5":
                        checkBalance();
                        break;
                    case "6":
                        showHistory();
                        break;
                    case "0":
                        running = false;
                        break;
                    default:
                        System.out.println("Unknown option");
                }
            } catch (BankingException ex) {
                System.out.println("Error: " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid input: " + ex.getMessage());
            }
        }
        System.out.println("Goodbye");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Create savings account");
        System.out.println("2. Create current account");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Check balance");
        System.out.println("6. Transaction history");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    private void createSavings() {
        String id = prompt("Account id (blank to auto-generate): ");
        if (id.isEmpty()) {
            id = IdGenerator.nextAccountId();
        }
        String holder = prompt("Holder name: ");
        double opening = readDouble("Opening balance: ");
        double rate = readDouble("Interest rate (% per month): ");
        Account a = bank.createSavingsAccount(id, holder, opening, rate);
        System.out.println("Created: " + a);
    }

    private void createCurrent() {
        String id = prompt("Account id (blank to auto-generate): ");
        if (id.isEmpty()) {
            id = IdGenerator.nextAccountId();
        }
        String holder = prompt("Holder name: ");
        double opening = readDouble("Opening balance: ");
        double overdraft = readDouble("Overdraft limit: ");
        Account a = bank.createCurrentAccount(id, holder, opening, overdraft);
        System.out.println("Created: " + a);
    }

    private void deposit() {
        String id = prompt("Account id: ");
        double amount = readDouble("Amount: ");
        bank.deposit(id, amount);
        System.out.println("New balance: " + bank.checkBalance(id));
    }

    private void withdraw() {
        String id = prompt("Account id: ");
        double amount = readDouble("Amount: ");
        bank.withdraw(id, amount);
        System.out.println("New balance: " + bank.checkBalance(id));
    }

    private void checkBalance() {
        String id = prompt("Account id: ");
        System.out.println("Balance: " + bank.checkBalance(id));
    }

    private void showHistory() {
        String id = prompt("Account id: ");
        for (Transaction txn : bank.getTransactionHistory(id)) {
            System.out.println(txn);
        }
    }

    private String prompt(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }

    private double readDouble(String label) {
        System.out.print(label);
        String line = scanner.nextLine().trim();
        try {
            return Double.parseDouble(line);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Not a number: " + line);
        }
    }
}
