package bankingSystem.model;

public class CurrentAccount extends BankAccount {

    private static final double OVERDRAFT_LIMIT = 5000.0;

    public CurrentAccount(
            int accountNumber,
            String accountHolder,
            double balance) {

        super(accountNumber, accountHolder, balance);

        if (balance < 0) {
            throw new IllegalArgumentException(
                    "Initial balance cannot be negative."
            );
        }
    }

    @Override
    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than 0."
            );
        }

        if (balance - amount < -OVERDRAFT_LIMIT) {
            throw new IllegalArgumentException(
                    "Withdrawal denied. Overdraft limit of "
                            + OVERDRAFT_LIMIT
                            + " exceeded."
            );
        }

        balance -= amount;

        getTransactions().add(
                new Transaction("WITHDRAW", amount)
        );
    }
}