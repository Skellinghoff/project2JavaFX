package com.example.project2JavaFX.Classes;

import java.io.Serial;

public class Supplier extends Customer {
    @Serial
    private static final long serialVersionUID = 8L;
    private final UserType userType = UserType.SUPPLER;

    public Supplier(User user, PersonalDetails personalDetails, BankAccount bankAccount) {
        this.user = user;
        this.personalDetails = personalDetails;
        this.bankAccount = bankAccount;
    }

    public Supplier(User user, PersonalDetails personalDetails) {
        this.user = user;
        this.personalDetails = personalDetails;
    }

    public Supplier() {

    }

    @Override
    public String toString() {
        if (bankAccount != null)
            return "Supplier={ user=" + user + ", personalDetails=" + personalDetails + ", bankAccount=" + bankAccount + " }";
        else
            return "Supplier={ user=" + user + ", personalDetails=" + personalDetails + " }";
    }
}
