package com.example.Delivery.Food;

import com.example.Delivery.Store.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Food {

    public enum Status {
        SELLING,
        SOLD_OUT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    private String name;
    private int price;
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('SELLING', 'SOLD_OUT') DEFAULT 'SELLING'")
    private Status status;

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}