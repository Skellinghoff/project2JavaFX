package com.example.project2JavaFX.Exceptions;

/**
 * OverdrawException exceptions are thrown by the
 * BankAccount class when the user attempts to
 * withdraw more money than is in their account
 */

public class OverdrawException extends Exception {
    /**
     * This constructor uses a generic
     * error message
     */

    public OverdrawException() {
        super("Error: Overdraw");
    }

    /**
     * This constructor specifies the overdrawn amount
     * in the error message
     * @param amount The bad overdrawn amount
     */

    public OverdrawException(double amount) {
        super(String.format("Error: Overdraw: %.2f", amount));
    }
}