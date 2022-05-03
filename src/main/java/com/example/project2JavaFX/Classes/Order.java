package com.example.project2JavaFX.Classes;

import java.io.Serializable;
import java.util.Arrays;

public class Order implements Serializable {
    private Item[] items;
    private String date;
    private String customerID;
    private String total;
    private String authNumber;

    public Order(Item[] items) {
        this.items = items;
    }
    public Order() {
    }

    @Override
    public String toString() {
        return "Order={ items={ " + Arrays.toString(items) + " }, date=" + date + ", customerID=" + customerID + ", total=" + total + ", authNumber=" + authNumber + " }";
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAuthNumber() {
        return authNumber;
    }

    public void setAuthNumber(String authNumber) {
        this.authNumber = authNumber;
    }
}
