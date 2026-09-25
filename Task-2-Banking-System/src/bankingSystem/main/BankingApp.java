package bankingSystem.main;

import bankingSystem.model.BankAccount;
import bankingSystem.service.Bank;

import java.util.Scanner;

public class BankingApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {

            System.out.println("\n===== Banking System =====");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Check Balance");
            System.out.println("7. View Transaction History");
            System.out.println("8. Generate Account Statement");
            System.out.println("9. Exit");

            int choice = readInt(scanner, "Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        createSavingsAccount(scanner, bank);
                        break;

                    case 2:
                        createCurrentAccount(scanner, bank);
                        break;

                    case 3:
                        deposit(scanner, bank);
                        break;

                    case 4:
                        withdraw(scanner, bank);
                        break;

                    case 5:
                        transfer(scanner, bank);
                        break;

                    case 6:
                        checkBalance(scanner, bank);
                        break;

                    case 7:
                        viewTransactions(scanner, bank);
                        break;

                    case 8:
                        generateStatement(scanner, bank);
                        break;

                    case 9:
                        System.out.println(
                                "Exiting Banking System..."
                        );

                        scanner.close();
                        return;

                    default:
                        System.out.println(
                                "Invalid choice. Please enter 1-9."
                        );
                }

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Transaction failed: " + e.getMessage()
                );
            }
        }
    }


    // Create Savings Account
    private static void createSavingsAccount(
            Scanner scanner,
            Bank bank) {

        System.out.println("\n----- Create Savings Account -----");

        String name = readName(
                scanner,
                "Enter account holder name: "
        );

        double initialBalance = readAmount(
                scanner,
                "Enter initial balance: "
        );

        BankAccount account = bank.createSavingsAccount(
                name,
                initialBalance
        );

        System.out.println(
                "Savings account created successfully."
        );

        System.out.println(
                "Account Number: "
                        + account.getAccountNumber()
        );
    }


    // Create Current Account
    private static void createCurrentAccount(
            Scanner scanner,
            Bank bank) {

        System.out.println("\n----- Create Current Account -----");

        String name = readName(
                scanner,
                "Enter account holder name: "
        );

        double initialBalance = readAmount(
                scanner,
                "Enter initial balance: "
        );

        BankAccount account = bank.createCurrentAccount(
                name,
                initialBalance
        );

        System.out.println(
                "Current account created successfully."
        );

        System.out.println(
                "Account Number: "
                        + account.getAccountNumber()
        );
    }


    // Deposit
    private static void deposit(
            Scanner scanner,
            Bank bank) {

        System.out.println("\n----- Deposit -----");

        int accountNumber = readInt(
                scanner,
                "Enter account number: "
        );

        double amount = readAmount(
                scanner,
                "Enter deposit amount: "
        );

        bank.deposit(
                accountNumber,
                amount
        );

        System.out.println(
                "Deposit successful."
        );
    }


    // Withdraw
    private static void withdraw(
            Scanner scanner,
            Bank bank) {

        System.out.println("\n----- Withdraw -----");

        int accountNumber = readInt(
                scanner,
                "Enter account number: "
        );

        double amount = readAmount(
                scanner,
                "Enter withdrawal amount: "
        );

        bank.withdraw(
                accountNumber,
                amount
        );

        System.out.println(
                "Withdrawal successful."
        );
    }


    // Transfer
    private static void transfer(
            Scanner scanner,
            Bank bank) {

        System.out.println("\n----- Transfer Money -----");

        int fromAccount = readInt(
                scanner,
                "Enter sender account number: "
        );

        int toAccount = readInt(
                scanner,
                "Enter receiver account number: "
        );

        double amount = readAmount(
                scanner,
                "Enter transfer amount: "
        );

        bank.transfer(
                fromAccount,
                toAccount,
                amount
        );

        System.out.println(
                "Transfer successful."
        );
    }


    // Check Balance
    private static void checkBalance(
            Scanner scanner,
            Bank bank) {

        System.out.println("\n----- Check Balance -----");

        int accountNumber = readInt(
                scanner,
                "Enter account number: "
        );

        double balance = bank.getBalance(
                accountNumber
        );

        System.out.printf(
                "Current Balance: %.2f%n",
                balance
        );
    }


    // View Transactions
    private static void viewTransactions(
            Scanner scanner,
            Bank bank) {

        System.out.println("\n----- Transaction History -----");

        int accountNumber = readInt(
                scanner,
                "Enter account number: "
        );

        BankAccount account =
                bank.findAccount(accountNumber);

        if (account == null) {

            throw new IllegalArgumentException(
                    "Account not found."
            );
        }

        if (account.getTransactions().isEmpty()) {

            System.out.println(
                    "No transactions found."
            );

            return;
        }

        for (var transaction :
                account.getTransactions()) {

            System.out.println(transaction);
        }
    }


    // Account Statement
    private static void generateStatement(
            Scanner scanner,
            Bank bank) {

        System.out.println("\n----- Account Statement -----");

        int accountNumber = readInt(
                scanner,
                "Enter account number: "
        );

        bank.getStatement(accountNumber);
    }


    // Read integer
    private static int readInt(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextInt()) {

                int value = scanner.nextInt();
                scanner.nextLine();

                return value;

            } else {

                System.out.println(
                        "Invalid input. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }


    // Read amount
    private static double readAmount(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextDouble()) {

                double amount = scanner.nextDouble();
                scanner.nextLine();

                if (amount > 0) {
                    return amount;
                }

                System.out.println(
                        "Amount must be greater than 0."
                );

            } else {

                System.out.println(
                        "Invalid amount. Please enter a number."
                );

                scanner.nextLine();
            }
        }
    }


    // Read account holder name
    private static String readName(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println(
                    "Name cannot be empty."
            );
        }
    }
}