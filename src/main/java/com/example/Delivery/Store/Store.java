package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;

import java.util.List;

public class Store {
    private String name;
    private List<Food> menu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getMenu() {
        return menu;
    }

    public void setMenu(List<Food> menu) {
        this.menu = menu;
    }
}
