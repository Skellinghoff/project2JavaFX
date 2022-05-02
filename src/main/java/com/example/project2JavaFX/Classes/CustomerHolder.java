package com.example.project2JavaFX.Classes;

public final class CustomerHolder {

    private Customer customer;
    private User user;
    private PersonalDetails personalDetails;
    private BankAccount bankAccount;
    private final static CustomerHolder INSTANCE = new CustomerHolder();

    private CustomerHolder() {
    }

    public static CustomerHolder getInstance() {
        return INSTANCE;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setPersonDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public PersonalDetails getPersonDetails() {
        return this.personalDetails;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }
}
