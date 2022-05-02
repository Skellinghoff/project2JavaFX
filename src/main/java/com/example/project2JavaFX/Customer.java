package com.example.project2JavaFX;

import java.io.Serializable;

public class Customer implements Serializable {
    private User user;
    private PersonalDetails personalDetails;
    private BankAccount bankAccount;

    public Customer(User user, PersonalDetails personalDetails, BankAccount bankAccount) {
        this.user = user;
        this.personalDetails = personalDetails;
        this.bankAccount = bankAccount;

    }

    public Customer(User user) {
        this.user = user;
    }

    public Customer(){}

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
}
