package com.example.project2JavaFX.Exceptions;

/**
 * NegativeBalanceSetException exceptions are thrown by the
 * BankAccount class when a negative balance is set
 */

public class NegativeBalanceSetException extends Exception {
    /**
     * This constructor uses a generic
     * error message.
     */

    public NegativeBalanceSetException() {
        super("Error: Negative set balance");
    }

    /**
     * This constructor specifies the bad set
     * balance in the error message.
     * @param amount The bad set balance.
     */

    public NegativeBalanceSetException(double amount) {
        super("Error: Negative set balance: " + amount);
    }
}
