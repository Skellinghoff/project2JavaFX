package com.example.project2JavaFX.Classes;

public class Supplier extends Customer {
    private User user;
    private PersonalDetails personalDetails;
    private BankAccount bankAccount;

    public Supplier(User user, PersonalDetails personalDetails, BankAccount bankAccount) {
        super(user, personalDetails, bankAccount);
    }

    public Supplier(User user, PersonalDetails personalDetails) {
        this.user = user;
        this.personalDetails = personalDetails;
    }

    @Override
    public String toString() {
        if (bankAccount != null)
            return "Customer={ user=" + user + ", personalDetails=" + personalDetails + ", bankAccount=" + bankAccount + " }";
        else
            return "Customer={ user=" + user + ", personalDetails=" + personalDetails + " }";
    }
}
