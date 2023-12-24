package com.example.expandablerecyclerviewapplication;

import java.util.ArrayList;

public interface UpdateSelectedItem {
    void addItem(String name,int price);

    ArrayList<OrderListModel> getItems();
}
