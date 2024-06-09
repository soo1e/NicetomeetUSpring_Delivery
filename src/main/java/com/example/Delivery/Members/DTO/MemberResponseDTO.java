package com.example.Delivery.Members.DTO;

import lombok.Getter;
import lombok.Setter;

// 요청에 따라 회원 데이터 전송을 하기 위한 DTO
@Getter
@Setter
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

}
