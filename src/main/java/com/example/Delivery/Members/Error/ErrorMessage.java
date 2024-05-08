package com.example.Delivery.Members.Error;

public enum ErrorMessage {
    MEMBER_NOT_FOUND("조회할 멤버가 없습니다."),
    MEMBER_REGISTRATION_FAILED("멤버 등록에 실패했습니다."),
    MEMBER_UPDATE_FAILED("멤버 수정에 실패했습니다."),
    MEMBER_DELETE_FAILED("멤버 삭제에 실패했습니다."),
    MEMBER_REGISTRATION_SUCCESS("멤버를 성공적으로 등록했습니다."),
    MEMBER_UPDATE_SUCCESS("멤버를 성공적으로 수정했습니다."),
    MEMBER_DELETE_SUCCESS("멤버 삭제가 성공적으로 완료되었습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
