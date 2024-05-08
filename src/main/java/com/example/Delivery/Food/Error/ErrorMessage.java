package com.example.Delivery.Food.Error;

public enum ErrorMessage {
    MENU_NOT_FOUND("조회할 메뉴가 없습니다."),
    STORE_NOT_FOUND("가게를 찾을 수 없습니다."),
    FAILED_TO_SAVE_MENU("메뉴 등록에 실패했습니다."),
    MENU_UPDATE_FAILED("메뉴 수정에 실패했습니다."),
    MENU_DELETE_SUCCESS("메뉴 삭제가 완료되었습니다."),
    MENU_SAVE_SUCCESS("메뉴를 성공적으로 등록했습니다."),
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

