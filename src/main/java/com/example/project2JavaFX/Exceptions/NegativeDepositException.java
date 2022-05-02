package com.example.project2JavaFX.Exceptions;

/**
 * NegativeDepositException exceptions are thrown by the
 * BankAccount class when a negative deposit amount is passed
 * to the deposit method
 */

public class NegativeDepositException extends Exception {
    /**
     * This constructor uses a generic
     * error message
     */

    public NegativeDepositException() {
        super("Error: Negative deposit");
    }

    /**
     * This constructor specifies the bad deposit
     * in the error message
     * @param amount The bad deposit amount
     */

    public NegativeDepositException(double amount) {
        super(String.format("Error: Negative deposit: %.2f", amount));
    }
}
