package com.example.project2JavaFX.Classes;

public class Supplier extends Customer {

    public Supplier(User user, PersonalDetails personalDetails, BankAccount bankAccount) {
        this.user = user;
        this.personalDetails = personalDetails;
        this.bankAccount = bankAccount;
    }

    public Supplier(User user, PersonalDetails personalDetails) {
        this.user = user;
        this.personalDetails = personalDetails;
    }

    @Override
    public String toString() {
        if (bankAccount != null)
            return "Supplier={ user=" + user + ", personalDetails=" + personalDetails + ", bankAccount=" + bankAccount + " }";
        else
            return "Supplier={ user=" + user + ", personalDetails=" + personalDetails + " }";
    }
}
