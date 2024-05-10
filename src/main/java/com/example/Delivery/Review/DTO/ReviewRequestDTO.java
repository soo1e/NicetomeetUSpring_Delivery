package com.example.Delivery.Review.DTO;

import lombok.Setter;

// 리뷰에 따라 store, member 정보를 가져오는 DTO입니다.
@Setter
public class ReviewRequestDTO {
    private int memberId;
    private int storeId;
    private double rating;
    private String content;

    public ReviewRequestDTO() {
    }

    public ReviewRequestDTO(int memberId, int storeId, double rating, String content) {
        this.memberId = memberId;
        this.storeId = storeId;
        this.rating = rating;
        this.content = content;
    }

    public long getMemberId() {
        return memberId;
    }

    public long getStoreId() {
        return storeId;
    }

    public double getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

}
