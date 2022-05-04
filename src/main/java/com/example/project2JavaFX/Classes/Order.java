package com.example.project2JavaFX.Classes;

import com.example.project2JavaFX.FileManagement;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Order implements Serializable {
    protected final DecimalFormat dfMoney = FileManagement.dfMoney;
    protected Item[] items;
    protected String date;
    protected String customerID;
    protected String total;
    protected String authNumber;

    protected String status;

    public Order(Item[] items) {
        this.items = items;
        this.status = "Ordered";
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
    public String getItemsString() {
        StringBuilder str = new StringBuilder();
        for (Item item : items) {
            str.append(item.getProduct().getName()).append("\n");
        }
        str.append("Total: ");
        return String.valueOf(str);
    }

    public String getQuantitiesString() {
        StringBuilder str = new StringBuilder();
        int sum = 0;
        for (Item item : items) {
            sum += item.getCount();
            str.append(item.getCount()).append("\n");
        }
        str.append(sum);
        return String.valueOf(str);
    }

    public String getTotalsString() {
        StringBuilder str = new StringBuilder();
        double sum = 0.0;
        for (Item item : items) {
            sum += (item.getCount() * item.getProduct().getRegularPrice());
            str.append("$").append(dfMoney.format(item.getCount() * item.getProduct().getRegularPrice())).append("\n");
        }
        str.append("$").append(dfMoney.format(sum));
        return String.valueOf(str);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
