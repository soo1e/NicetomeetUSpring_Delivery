package com.example.Delivery.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final SpringDataJPAReviewRepository reviewRepository;

    @Autowired
    public ReviewService(SpringDataJPAReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // 리뷰 작성 메소드
    public Review writeReview(int memberId, int storeId, double rating, String content) {
        return reviewRepository.writeReview(memberId, storeId, rating, content);
    }



    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByMemberId(int memberId) {
        return reviewRepository.findByMemberId(memberId);
    }

    public List<Review> getReviewsByStoreId(int storeId) {
        return reviewRepository.findByStoreId(storeId);
    }

    public Review updateReview(int reviewId, int memberId, int storeId, double rating, String content) {
        if (reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("해당 id의 리뷰를 찾을 수 없습니다."));
            review.setMemberId(memberId);
            review.setStoreId(storeId);
            review.setRating(rating);
            review.setContent(content);
            return reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("해당 id의 리뷰가 존재하지 않습니다.");
        }
    }


    public void deleteReview(int reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new IllegalArgumentException("해당 id의 리뷰가 존재하지 않습니다.");
        }
    }
}