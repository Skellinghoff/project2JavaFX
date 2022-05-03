package com.example.project2JavaFX.Classes;

import com.example.project2JavaFX.FileManagement;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

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

    @Override
    public String toString() {
        return "Customer={ user=" + user + ", personalDetails=" + personalDetails + ", bankAccount=" + bankAccount + ", orders={ " + Arrays.toString(orders) + " } }";
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

    public void addOrder(Order order) {
        try {
            FileManagement.addOrder(this, order);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
