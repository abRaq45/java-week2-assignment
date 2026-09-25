package bankingSystem.model;

import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {

    private int accountNumber;
    private String accountHolder;
    protected double balance;

    private List<Transaction> transactions = new ArrayList<>();


    // Constructor
    public BankAccount(
            int accountNumber,
            String accountHolder,
            double balance) {

        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }


    // Get account number
    public int getAccountNumber() {
        return accountNumber;
    }


    // Get account holder
    public String getAccountHolder() {
        return accountHolder;
    }


    // Get balance
    public double getBalance() {
        return balance;
    }


    // Deposit money
    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be greater than 0."
            );
        }

        balance += amount;

        transactions.add(
                new Transaction(
                        "DEPOSIT",
                        amount
                )
        );
    }


    // Withdraw money
    public abstract void withdraw(double amount);


    // Get transaction history
    public List<Transaction> getTransactions() {
        return transactions;
    }


    // Generate account statement
    public void getStatement() {

        System.out.println("\n===== Account Statement =====");

        System.out.println(
                "Account Number : " + accountNumber
        );

        System.out.println(
                "Account Holder : " + accountHolder
        );

        System.out.println(
                "Current Balance: " + balance
        );

        System.out.println("\nTransaction History:");

        if (transactions.isEmpty()) {

            System.out.println("No transactions found.");

            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}