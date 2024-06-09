package com.example.Delivery.Store.DTO;

import jakarta.validation.constraints.*;

public class StoreRequestDTO {

    @NotBlank(message = "가게 이름을 입력하세요.")
    private String name;

    @NotNull(message = "카테고리 ID를 입력하세요.")
    private Integer categoryId;

    @NotBlank(message = "전화번호를 입력하세요.")
    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}", message = "올바른 전화번호 형식이 아닙니다.")
    private String phoneNumber;

    @NotBlank(message = "운영 시간을 입력하세요.")
    private String operatingHours;

    @NotNull(message = "최소 주문 금액을 입력하세요.")
    @Min(value = 0, message = "최소 주문 금액은 0 이상이어야 합니다.")
    private Integer minOrderAmount;

    @NotNull(message = "평점을 입력하세요.")
    @DecimalMin(value = "0.0", message = "평점은 0.0 이상이어야 합니다.")
    @DecimalMax(value = "5.0", message = "평점은 5.0 이하여야 합니다.")
    private Double rating;

    public StoreRequestDTO() {
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
}