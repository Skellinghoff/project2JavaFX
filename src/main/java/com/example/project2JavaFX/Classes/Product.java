package com.example.project2JavaFX.Classes;

import java.io.Serial;
import java.io.Serializable;
import java.text.DecimalFormat;

public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 7L;
    private String name;
    private String description;
    private double regularPrice;
    private double salePercent = 0.00;
    private boolean onSale = false;
    private double salePrice;
    private int stockReserved;
    private int stockAvailable;

    private int totalStock;

    public Product(String name, String description, double regularPrice, int stockReserved, int stockAvailable) {
        this.name = name;
        this.description = description;
        this.regularPrice = regularPrice;
        this.stockReserved = stockReserved;
        this.stockAvailable = stockAvailable;
        this.totalStock = stockAvailable + stockReserved;
    }

    public Product(String name, String description, double regularPrice, double salePercent, boolean onSale, int stockReserved, int stockAvailable) {
        this.name = name;
        this.description = description;
        this.regularPrice = regularPrice;
        this.salePercent = salePercent;
        this.onSale = onSale;
        this.salePrice = getSalePrice();
        this.stockReserved = stockReserved;
        this.stockAvailable = stockAvailable;
    }

    public Product() {}

    @Override
    public String toString() {
        return "Product={ name=" + name + ", description=" + description + ", regularPrice=" + regularPrice +
                ", salePercent=" + salePercent + ", onSale=" + onSale + ", salePrice=" + salePrice + " }";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRegularPrice(String regularPrice) {
        DecimalFormat dfMoney = new DecimalFormat("0.00");
        this.regularPrice = Double.parseDouble(dfMoney.format(regularPrice));
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public void setSalePrice(String salePrice) {
        DecimalFormat dfMoney = new DecimalFormat("0.00");
        this.salePrice = Double.parseDouble(dfMoney.format(salePrice));
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setSalePercent(double salePercent) {
        this.salePercent = salePercent;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getRegularPrice() {
        return regularPrice;
    }
    public String getRegularPriceString() {
        return String.valueOf(regularPrice);
    }

    public double getSalePrice() {
        DecimalFormat dfMoney = new DecimalFormat("0.00");
        return Double.parseDouble(dfMoney.format(regularPrice * salePercent));
    }

    public double getSalePercent() {
        return salePercent;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public String getStockReservedString() {
        return String.valueOf(stockReserved);
    }
    public int getStockReserved() {
        return stockReserved;
    }

    public void setStockReserved(int stockReserved) {
        this.stockReserved = stockReserved;
    }

    public String getStockAvailableString() {
        return String.valueOf(stockAvailable);
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public String getTotalStockString() {
        return String.valueOf(totalStock);
    }

    public int getStockTotal() {
        return totalStock;
    }

    public void setStockTotal(int totalStock) {
        this.totalStock = totalStock;
    }
}
