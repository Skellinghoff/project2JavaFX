package com.example.project2JavaFX.Classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Item {
    private Product product;
    private final IntegerProperty count = new SimpleIntegerProperty();

    public Item(Product product, int count) {
        this.product = product;
        this.count.setValue(count);
    }
    @Override
    public String toString() {
        return Integer.toString(getCount());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int count) {
        this.count.setValue(count);
    }

    public IntegerProperty itemCountProperty() {
        return this.count;
    }
}
