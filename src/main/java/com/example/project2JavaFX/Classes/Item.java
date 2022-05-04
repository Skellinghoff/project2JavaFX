package com.example.project2JavaFX.Classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;

public class Item implements Serializable {
    protected Product product;
    protected int count;

    public Item(Product product, int count) {
        this.product = product;
        this.count = count;
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
