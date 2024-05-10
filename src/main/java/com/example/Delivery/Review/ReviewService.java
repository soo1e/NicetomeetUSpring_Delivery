package com.example.Delivery.Review;

import com.example.Delivery.Members.SpringDataJPAMembersRepository;
import com.example.Delivery.Review.Error.ErrorMessage;
import com.example.Delivery.Store.SpringDataJPAStoreRepository;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private final SpringDataJPAReviewRepository reviewRepository;
    private final SpringDataJPAStoreRepository storeRepository;
    private final SpringDataJPAMembersRepository membersRepository;
    private final Validator validator;

    // 전체 리뷰 조회
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // memberId로 리뷰 조회
    public List<Review> getReviewsByMemberId(int memberId) {
        return reviewRepository.findByMemberId(memberId);
    }

    // storeId로 리뷰 조회
    public List<Review> getReviewsByStoreId(int storeId) {
        return reviewRepository.findByStoreId(storeId);
    }

    // 리뷰 작성
    public Review writeReview(long memberId, long storeId, double rating, String content) {
        if (rating < 0 || rating > 5) { // 유효 범위를 벗어나는 경우
            throw new IllegalArgumentException("평점은 0에서 5 사이의 값이어야 합니다.");
        }

        // 멤버 ID가 존재하는지 확인
        if (!membersRepository.existsById(memberId)) {
            throw new IllegalArgumentException(ErrorMessage.MEMBER_NOT_FOUND.getMessage());
        }

        // 가게 ID가 존재하는지 확인
        if (!storeRepository.existsById((storeId))) {
            throw new IllegalArgumentException(ErrorMessage.STORE_NOT_FOUND.getMessage());
        }

        // 리뷰 작성
        return reviewRepository.writeReview(memberId, storeId, rating, content);
    }

    // 리뷰 수정
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

    // 리뷰 삭제
    public void deleteReview(int reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new IllegalArgumentException("해당 id의 리뷰가 존재하지 않습니다.");
        }
    }

}
