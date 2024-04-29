package com.example.Delivery.Review.DTO;

// 리뷰에 따라 store, member 정보를 가져오는 DTO입니다.
public class ReviewRequest {
    private int memberId;
    private int storeId;
    private double rating;
    private String content;

    public ReviewRequest() {
    }

    public ReviewRequest(int memberId, int storeId, double rating, String content) {
        this.memberId = memberId;
        this.storeId = storeId;
        this.rating = rating;
        this.content = content;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
