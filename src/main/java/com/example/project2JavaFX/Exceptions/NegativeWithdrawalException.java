package com.example.project2JavaFX.Exceptions;

/**
 * NegativeWithdrawalException exceptions are thrown by the
 * BankAccount class when a negative withdraw amount is passed
 * to the withdraw method
 */

public class NegativeWithdrawalException extends Exception {
    /**
     * This constructor uses a generic
     * error message
     */

    public NegativeWithdrawalException() {
        super("Error: Negative withdrawal");
    }

    /**
     * This constructor specifies the bad withdrawal
     * in the error message
     * @param amount The bad withdrawal amount
     */

    public NegativeWithdrawalException(double amount) {
        super(String.format("Error: Negative withdraw: %.2f", amount));
    }
}
