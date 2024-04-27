package com.example.Delivery.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    // 리뷰 작성
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestParam("memberId") int memberId, @RequestParam("storeId") int storeId, @RequestParam("rating") double rating, @RequestParam("content") String content) {
        // 회원 ID와 가게 ID를 사용하여 리뷰 생성
        Review createdReview = reviewService.writeReview(memberId, storeId, rating, content);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }


    // 전체 리뷰 조회
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // memberId에 따른 리뷰 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Review>> getReviewsByMemberId(@PathVariable("memberId") int memberId) {
        List<Review> reviews = reviewService.getReviewsByMemberId(memberId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // storeId에 따른 리뷰 조회
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Review>> getReviewsByStoreId(@PathVariable("storeId") int storeId) {
        List<Review> reviews = reviewService.getReviewsByStoreId(storeId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable("reviewId") int reviewId, @RequestParam("memberId") int memberId, @RequestParam("storeId") int storeId, @RequestParam("rating") double rating, @RequestParam("content") String content) {
        Review updatedReview = reviewService.updateReview(reviewId, memberId, storeId, rating, content);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") int reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("리뷰 삭제가 완료되었습니다");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
