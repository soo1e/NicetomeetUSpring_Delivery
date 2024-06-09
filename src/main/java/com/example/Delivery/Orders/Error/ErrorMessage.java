package com.example.Delivery.Orders.Error;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    ORDER_NOT_FOUND("조회할 주문이 없습니다."),
    MEMBER_NOT_FOUND("해당 memberId에 해당하는 회원을 찾을 수 없습니다."),
    ORDER_DELETE_FAILED("주문 삭제에 실패했습니다."),
    ORDER_DELETE_SUCCESS("주문 삭제가 성공적으로 완료되었습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
