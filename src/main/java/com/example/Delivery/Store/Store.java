package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;
import jakarta.persistence.*;
import java.util.List;


@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    private String name;
    private Integer categoryId;
    private String phoneNumber;
    private String operatingHours;
    private Integer minOrderAmount;
    private Double rating;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Food> menu;

    public Store() {}

    public Store(String name, Integer categoryId, String phoneNumber, String operatingHours, Integer minOrderAmount, Double rating) {
        this.name = name;
        this.categoryId = categoryId;
        this.phoneNumber = phoneNumber;
        this.operatingHours = operatingHours;
        this.minOrderAmount = minOrderAmount;
        this.rating = rating;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public List<Food> getMenu() {
        return menu;
    }

    public Integer getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(Integer minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setMenu(List<Food> menu) {
    }
}