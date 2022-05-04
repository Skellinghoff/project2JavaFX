package com.example.project2JavaFX.Classes;

import java.io.Serial;
import java.io.Serializable;

public class PersonalDetails implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;
    protected String name;
    protected String address;
    protected String securityQuestion;
    protected String securityAnswer;

    public PersonalDetails() {
    }

    public PersonalDetails(String name, String address, String securityQuestion, String securityAnswer) {
        this.name = name;
        this.address = address;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public PersonalDetails(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonalDetails={ name=" + name + ", address=" + address + ", securityQuestion=" + securityQuestion + ", securityAnswer=" + securityAnswer + " }";
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
}
