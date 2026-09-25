package bankingSystem.service;



import bankingSystem.model.BankAccount;
import bankingSystem.model.CurrentAccount;
import bankingSystem.model.SavingsAccount;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<Integer, BankAccount> accounts = new HashMap<>();

    private int nextAccountNumber = 1001;


    // Create Savings Account
    public BankAccount createSavingsAccount(
            String accountHolder,
            double initialBalance) {

        int accountNumber = nextAccountNumber++;

        BankAccount account = new SavingsAccount(
                accountNumber,
                accountHolder,
                initialBalance
        );

        accounts.put(accountNumber, account);

        return account;
    }


    // Create Current Account
    public BankAccount createCurrentAccount(
            String accountHolder,
            double initialBalance) {

        int accountNumber = nextAccountNumber++;

        BankAccount account = new CurrentAccount(
                accountNumber,
                accountHolder,
                initialBalance
        );

        accounts.put(accountNumber, account);

        return account;
    }


    // Find account
    public BankAccount findAccount(int accountNumber) {

        return accounts.get(accountNumber);
    }


    // Deposit
    public void deposit(
            int accountNumber,
            double amount) {

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException(
                    "Account not found."
            );
        }

        account.deposit(amount);
    }


    // Withdraw
    public void withdraw(
            int accountNumber,
            double amount) {

        BankAccount account = findAccount(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException(
                    "Account not found."
            );
        }

        account.withdraw(amount);
    }


    // Transfer
    public void transfer(
            int fromAccountNumber,
            int toAccountNumber,
            double amount) {

        BankAccount fromAccount =
                findAccount(fromAccountNumber);

        BankAccount toAccount =
                findAccount(toAccountNumber);


        if (fromAccount == null) {

            throw new IllegalArgumentException(
                    "Source account not found."
            );
        }


        if (toAccount == null) {

            throw new IllegalArgumentException(
                    "Destination account not found."
            );
        }


        if (fromAccountNumber == toAccountNumber) {

            throw new IllegalArgumentException(
                    "Cannot transfer money to the same account."
            );
        }


        // Withdraw from source account
        fromAccount.withdraw(amount);

        // Deposit into destination account
        toAccount.deposit(amount);
    }


    // Display account balance
    public double getBalance(int accountNumber) {

        BankAccount account = findAccount(accountNumber);

        if (account == null) {

            throw new IllegalArgumentException(
                    "Account not found."
            );
        }

        return account.getBalance();
    }


    // Display account statement
    public void getStatement(int accountNumber) {

        BankAccount account = findAccount(accountNumber);

        if (account == null) {

            throw new IllegalArgumentException(
                    "Account not found."
            );
        }

        account.getStatement();
    }
}