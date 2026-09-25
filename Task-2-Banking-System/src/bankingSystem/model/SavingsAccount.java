package bankingSystem.model;

public class SavingsAccount extends BankAccount {

    private static final double MINIMUM_BALANCE = 1000.0;

    public SavingsAccount(
            int accountNumber,
            String accountHolder,
            double balance) {

        super(accountNumber, accountHolder, balance);

        if (balance < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(
                    "Savings account must maintain a minimum balance of "
                            + MINIMUM_BALANCE
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

        if (balance - amount < MINIMUM_BALANCE) {
            throw new IllegalArgumentException(
                    "Withdrawal denied. Minimum balance of "
                            + MINIMUM_BALANCE
                            + " must be maintained."
            );
        }

        balance -= amount;

        getTransactions().add(
                new Transaction("WITHDRAW", amount)
        );
    }
}