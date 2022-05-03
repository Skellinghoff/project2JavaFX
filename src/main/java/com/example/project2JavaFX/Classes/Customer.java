package com.example.project2JavaFX.Classes;

import java.io.Serializable;

public class Customer implements Serializable {
    private User user;
    private PersonalDetails personalDetails;
    private BankAccount bankAccount;
    private Order[] orders;

    public Customer(User user, PersonalDetails personalDetails, BankAccount bankAccount) {
        this.user = user;
        this.personalDetails = personalDetails;
        this.bankAccount = bankAccount;

    }

    public Customer(User user) {
        this.user = user;
    }

    public Customer() {
        this.user = null;
        this.personalDetails = null;
        this.bankAccount = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PersonalDetails getPersonDetails() {
        return personalDetails;
    }

    public void setPersonDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }
}
