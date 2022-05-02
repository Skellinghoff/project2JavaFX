package com.example.project2JavaFX.Classes;

import java.io.Serializable;

public class PersonalDetails implements Serializable {
    private String name;
    private String address;
    private String securityQuestion;
    private String securityAnswer;

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
