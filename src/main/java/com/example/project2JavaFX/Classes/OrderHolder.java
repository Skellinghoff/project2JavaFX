package com.example.project2JavaFX.Classes;

public final class OrderHolder {
    private Order order;
    private final static OrderHolder INSTANCE = new OrderHolder();
    private OrderHolder() {}

    public static OrderHolder getInstance() {
        return INSTANCE;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }

}
