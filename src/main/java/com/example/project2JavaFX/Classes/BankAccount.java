package com.example.project2JavaFX.Classes;

import com.example.project2JavaFX.Exceptions.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * The BankAccount class simulates a bank account.
 */

public class BankAccount implements Serializable {
    private double balance;      // Account balance
    private String creditCardNumber;

    /**
     * This constructor sets the starting balance at 0.0.
     */

    public BankAccount() {
        balance = 0.0;
    }

    /**
     * This constructor sets the starting balance to the value passed as an argument.
     *
     * @param startBalance The starting balance.
     * @throws NegativeStartingBalanceException When startBalance is negative.
     */

    public BankAccount(double startBalance, String creditCardNumber) throws NegativeStartingBalanceException {
        if (startBalance < 0)
            throw new NegativeStartingBalanceException(startBalance);

        this.creditCardNumber = creditCardNumber;
        balance = startBalance;
    }

    public BankAccount(String creditCardNumber) {
        DecimalFormat dfMoney = new DecimalFormat("0.00");

        balance = Double.parseDouble(dfMoney.format(new Random().nextDouble(1000, 1000000)));
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * This constructor sets the starting balance
     * to the value in the String argument.
     *
     * @param startBalance The starting balance, as a String.
     */

    public BankAccount(String startBalance, String creditCardNumber) throws NegativeStartingBalanceException {
        double b = Double.parseDouble(startBalance);
        if (b < 0)
            throw new NegativeStartingBalanceException(b);

        this.creditCardNumber = creditCardNumber;
        balance = b;
    }

    /**
     * The deposit method makes a deposit into the account.
     *
     * @param amount The amount to add to the balance field.
     * @throws NegativeDepositException When amount is negative.
     */

    public void deposit(double amount) throws NegativeDepositException {
        if (amount < 0)
            throw new NegativeDepositException(amount);

        balance += amount;
    }

    /**
     * The withdraw method withdraws an amount from the account.
     *
     * @param amount The amount to subtract from the balance field.
     * @throws NegativeWithdrawalException When amount is negative.
     * @throws OverdrawException           When amount is greater than the account balance.
     */

    public void withdraw(double amount) throws NegativeWithdrawalException, OverdrawException {
        if (amount > balance)
            throw new OverdrawException(amount);

        if (amount < 0)
            throw new NegativeWithdrawalException(amount);

        balance -= amount;
    }

    /**
     * The setBalance method sets the account balance.
     *
     * @param balance The value to store in the balance field.
     * @throws NegativeBalanceSetException When balance is negative
     */

    public void setBalance(double balance) throws NegativeBalanceSetException {
        if (balance < 0)
            throw new NegativeBalanceSetException(balance);

        this.balance = balance;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public String toString() {
        return String.format("Bank Account: " +
                "\n\tBalance: %.2f", balance);
    }
}


