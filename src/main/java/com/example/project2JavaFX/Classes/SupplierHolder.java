package com.example.project2JavaFX.Classes;

public final class SupplierHolder {

    private Supplier supplier;
    private User user;
    private PersonalDetails personalDetails;
    private BankAccount bankAccount;
    private final static SupplierHolder INSTANCE = new SupplierHolder();

    private SupplierHolder() {
    }

    public static SupplierHolder getInstance() {
        return INSTANCE;
    }

    public void setCustomer(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return this.supplier;
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
