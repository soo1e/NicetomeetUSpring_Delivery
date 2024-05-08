package com.example.Delivery.Members.DTO;

// 요청에 따라 회원 데이터 전송을 하기 위한 DTO
public class MemberResponseDTO {
    private Long memberId;
    private String username;
    private String phoneNumber;
    private String address;

    public MemberResponseDTO(Long memberId, String username, String phoneNumber, String address) {
        this.memberId = memberId;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
