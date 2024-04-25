package com.example.Delivery.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJPAReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByMemberId(long memberId);
    List<Review> findByStoreId(long storeId);

    // 리뷰 작성 메소드
    default Review writeReview(int memberId, int storeId, double rating, String content) {
        Review review = new Review();
        review.setMemberId(memberId);
        review.setStoreId(storeId);
        review.setRating(rating);
        review.setContent(content);
        return save(review);
    }
}
