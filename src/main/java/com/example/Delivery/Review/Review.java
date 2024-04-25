package com.example.Delivery.Review;

import com.example.Delivery.Members.Members;
import com.example.Delivery.Store.Store;
import jakarta.persistence.*;


@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    private int memberId;
    private int storeId;

    private double rating;
    private String content;

    public Review() {

    }

    public Review(int reviewId, int memberId, int storeId, double rating, String content) {
        this.reviewId = reviewId;
        this.memberId = memberId;
        this.storeId = storeId;
        this.rating = rating;
        this.content = content;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getStoreId() {
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
