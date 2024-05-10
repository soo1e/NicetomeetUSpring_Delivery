package com.example.Delivery.Review.Error;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    REVIEW_NOT_FOUND("조회할 리뷰가 없습니다."),
    MEMBER_NOT_FOUND("멤버 ID가 존재하지 않습니다."),
    STORE_NOT_FOUND("가게 ID가 존재하지 않습니다."),
    REVIEW_WRITE_ERROR("리뷰 작성 중 오류가 발생했습니다."),
    REVIEW_UPDATE_ERROR("리뷰 수정 중 오류가 발생했습니다."),
    REVIEW_DELETE_ERROR("리뷰 삭제 중 오류가 발생했습니다."),
    REVIEW_DELETE_SUCCESS("리뷰 삭제가 성공적으로 완료되었습니다."),
    RATE_RANGE_ERROR("평점은 0에서 5 사이의 값이어야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
