package com.example.expandablerecyclerviewapplication;

public class OrderListModel {

    private String name;
    private int price;

    public OrderListModel(String name,int price) {
        this.name = name;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public int price() {
        return price;
    }
}
