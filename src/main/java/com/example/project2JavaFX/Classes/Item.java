package com.example.project2JavaFX.Classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serial;
import java.io.Serializable;

public class Item implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    protected Product product;
    protected int count;
    protected int maxCount;
    public Item(Product product) {
        this.product = product;
        this.count = 0;
        this.maxCount = product.getStockReserved();
    }

    @Override
    public String toString() {
        return "Item={ product=" + product + ", count=" + getCountString() + ", itemTotal=" + getItemTotal() + " }";
    }

    public String getCountString() {
        return Integer.toString(getCount());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMaxCountString() {
        return String.valueOf(maxCount);
    }

    public IntegerProperty getMaxCount() {
        return new SimpleIntegerProperty(this.maxCount);
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public String getItemTotal() {
        double itemPrice;
        if (product.isOnSale())
            itemPrice = product.getSalePrice();
        else
            itemPrice = product.getRegularPrice();

        return Double.toString(itemPrice * getCount());
    }

    public IntegerProperty itemCountProperty() {
//        SimpleIntegerProperty c = new SimpleIntegerProperty(this.count);
        return new SimpleIntegerProperty(this.count);
    }
}
