package com.example.project2JavaFX.Classes;

import java.io.Serializable;

public class User implements Serializable {
    protected String id;
    protected String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User={ id=" + id + ", password=" + password + " }";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
