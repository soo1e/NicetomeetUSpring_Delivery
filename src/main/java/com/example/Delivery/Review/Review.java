package com.example.Delivery.Review;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @NotNull(message = "멤버 ID는 필수 입력 항목입니다.")
    private long memberId;

    @NotNull(message = "가게 ID는 필수 입력 항목입니다.")
    private long storeId;


    @Min(value = 0, message = "평점은 0보다 작을 수 없습니다.")
    @Max(value = 5, message = "평점은 5보다 클 수 없습니다.")
    private double rating;

    @NotBlank(message = "리뷰 내용은 필수 입력 항목입니다.")
    private String content;

    public Review() {

    }

    public Review(long reviewId, long memberId, long storeId, double rating, String content) {
        this.reviewId = reviewId;
        this.memberId = memberId;
        this.storeId = storeId;
        this.rating = rating;
        this.content = content;
    }

}
