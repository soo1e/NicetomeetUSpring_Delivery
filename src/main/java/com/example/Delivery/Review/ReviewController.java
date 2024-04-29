package com.example.Delivery.Review;

import com.example.Delivery.Members.SpringDataJPAMembersRepository;
import com.example.Delivery.Review.DTO.ReviewRequest;
import com.example.Delivery.Store.SpringDataJPAStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;
    private SpringDataJPAStoreRepository storeRepository;
    private SpringDataJPAMembersRepository membersRepository;

    @Autowired
    public ReviewController(ReviewService reviewService, SpringDataJPAStoreRepository storeRepository, SpringDataJPAMembersRepository membersRepository) {
        this.reviewService = reviewService;
        this.storeRepository = storeRepository;
        this.membersRepository = membersRepository;
    }

    // 전체 리뷰 조회
    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        List<Review> reviewsList = reviewService.getAllReviews();
        if (!reviewsList.isEmpty()) {
            return new ResponseEntity<>(reviewsList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 리뷰가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // memberId에 따른 리뷰 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> getReviewsByMemberId(@PathVariable("memberId") int memberId) {
        List<Review> reviewsList = reviewService.getReviewsByMemberId(memberId);
        if (!reviewsList.isEmpty()) {
            return new ResponseEntity<>(reviewsList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 리뷰가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // storeId에 따른 리뷰 조회
    @GetMapping("/store/{storeId}")
    public ResponseEntity<?> getReviewsByStoreId(@PathVariable("storeId") int storeId) {
        List<Review> reviewsList = reviewService.getReviewsByStoreId(storeId);
        if (!reviewsList.isEmpty()) {
            return new ResponseEntity<>(reviewsList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 리뷰가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 리뷰 작성
    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody ReviewRequest request) {
        try {
            // 멤버 ID가 존재하는지 확인
            if (!membersRepository.existsById(request.getMemberId())) {
                throw new IllegalArgumentException("멤버 ID가 존재하지 않습니다.");
            }

            // 가게 ID가 존재하는지 확인
            if (!storeRepository.existsById(request.getStoreId())) {
                throw new IllegalArgumentException("가게 ID가 존재하지 않습니다.");
            }

            // DTO로부터 데이터를 추출하여 리뷰 생성
            Review createdReview = reviewService.writeReview((int)request.getMemberId(), (int)request.getStoreId(), request.getRating(), request.getContent());
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("리뷰 작성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }



    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable("reviewId") int reviewId, @RequestParam("memberId") int memberId, @RequestParam("storeId") int storeId, @RequestParam("rating") double rating, @RequestParam("content") String content) {
        try {
            Review updatedReview = reviewService.updateReview(reviewId, memberId, storeId, rating, content);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") int reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok("리뷰 삭제가 완료되었습니다");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

}
