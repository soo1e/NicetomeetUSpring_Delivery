package com.example.Delivery.Store.Error;

public enum ErrorMessage {
    STORE_NOT_FOUND("조회할 가게가 없습니다."),
    STORE_REGISTRATION_FAILED("가게 등록에 실패했습니다."),
    STORE_UPDATE_FAILED("가게 수정에 실패했습니다."),
    STORE_DELETE_FAILED("가게 삭제에 실패했습니다."),
    STORE_DELETE_SUCCESS("가게 삭제가 완료되었습니다");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
