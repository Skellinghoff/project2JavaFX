package com.example.project2JavaFX.Classes;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Item[] items;
    private Date date;
    private String customerID;
    private double total;
    private int authNumber;

    public Order(Item[] items) {
        this.items = items;
    }
    public Order() {
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getAuthNumber() {
        return authNumber;
    }

    public void setAuthNumber(int authNumber) {
        this.authNumber = authNumber;
    }
}
