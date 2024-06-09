package com.example.Delivery.Review;

import com.example.Delivery.Members.SpringDataJPAMembersRepository;
import com.example.Delivery.Review.DTO.ReviewRequestDTO;
import com.example.Delivery.Review.Error.ErrorMessage;
import com.example.Delivery.Store.SpringDataJPAStoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reviews")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;
    private SpringDataJPAStoreRepository storeRepository;
    private SpringDataJPAMembersRepository membersRepository;


    // 전체 리뷰 조회
    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.REVIEW_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
    }

    // memberId에 따른 리뷰 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> getReviewsByMemberId(@PathVariable("memberId") int memberId) {
        List<Review> reviews = reviewService.getReviewsByMemberId(memberId);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.REVIEW_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
    }

    // storeId에 따른 리뷰 조회
    @GetMapping("/store/{storeId}")
    public ResponseEntity<?> getReviewsByStoreId(@PathVariable("storeId") int storeId) {
        List<Review> reviews = reviewService.getReviewsByStoreId(storeId);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.REVIEW_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
    }

    // 리뷰 작성
    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewRequestDTO request) {
        try {
            // 유효성 검사를 통해 예외 발생 여부 확인
            if (request.getRating() < 0 || request.getRating() > 5) {
                throw new IllegalArgumentException(ErrorMessage.RATE_RANGE_ERROR.getMessage());
            }

            // 멤버 ID가 존재하는지 확인
            if (!membersRepository.existsById((long) request.getMemberId())) {
                throw new IllegalArgumentException(ErrorMessage.MEMBER_NOT_FOUND.getMessage());
            }

            // 가게 ID가 존재하는지 확인
            if (!storeRepository.existsById((long) request.getStoreId())) {
                throw new IllegalArgumentException(ErrorMessage.STORE_NOT_FOUND.getMessage());
            }

            // DTO로부터 데이터를 추출하여 리뷰 생성
            Review createdReview = reviewService.writeReview(request.getMemberId(), request.getStoreId(), request.getRating(), request.getContent());
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 예외 메시지 반환
        }
    }



    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable("reviewId") int reviewId, @RequestParam("memberId") int memberId, @RequestParam("storeId") int storeId, @RequestParam("rating") double rating, @RequestParam("content") String content) {
        try {
            Review updatedReview = reviewService.updateReview(reviewId, memberId, storeId, rating, content);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.REVIEW_UPDATE_ERROR.getMessage() + e.getMessage());
        }
    }


    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") int reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok(ErrorMessage.REVIEW_DELETE_SUCCESS.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessage.REVIEW_DELETE_ERROR.getMessage() + e.getMessage());
        }
    }
}
