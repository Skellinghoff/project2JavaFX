package com.example.project2JavaFX.Exceptions;

/**
 * NegativeStartingBalanceException exceptions are thrown by the
 * BankAccount class when a negative starting balance is
 * passed to the constructor.
 */

public class NegativeStartingBalanceException extends Exception {
    /**
     * This constructor uses a generic
     * error message.
     */

    public NegativeStartingBalanceException() {
        super("Error: Negative starting balance");
    }

    /**
     * This constructor specifies the bad starting
     * balance in the error message.
     *
     * @param amount The bad starting balance.
     */

    public NegativeStartingBalanceException(double amount) {
        super(String.format("Error: Negative starting balance: %.2f", amount));
    }
}
