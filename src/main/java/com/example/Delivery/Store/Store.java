package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;
import com.example.Delivery.Review.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;


@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @NotBlank(message = "가게 이름은 필수 입력 항목입니다.")
    private String name;

    @NotNull(message = "카테고리 ID는 필수 입력 항목입니다.")
    private Integer categoryId;

    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}", message = "올바른 전화번호 형식이 아닙니다. (XX-XXXX-XXXX)")
    private String phoneNumber;
    private String operatingHours;
    private Integer minOrderAmount;
    private Double rating;

    @Column(name = "average_rating")
    private Double averageRating;

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

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    // Review들의 평균 rating을 계산하는 메서드
    public void calculateAverageRating(List<Review> reviews) {
        if (reviews != null && !reviews.isEmpty()) {
            double sum = 0.0;
            for (Review review : reviews) {
                sum += review.getRating();
            }
            averageRating = sum / reviews.size();
        } else {
            averageRating = null;
        }
    }
}