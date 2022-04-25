package com.example.project2JavaFX;

public class Customer {
    private final String id;
    private final String password;
    private final String name;
    private final String address;
    private final String creditCardNumber;
    private final String securityQuestion;
    private final String securityAnswer;

    public Customer(String id, String password, String name, String address, String creditCardNumber, String securityQuestion, String securityAnswer) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }
}
