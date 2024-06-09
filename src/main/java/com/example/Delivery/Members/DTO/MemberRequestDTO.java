package com.example.Delivery.Members.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberRequestDTO {
    @NotBlank(message = "사용자 이름은 필수 입력입니다.")
    @Size(min = 3, max = 50, message = "사용자 이름은 3자 이상 50자 이하여야 합니다.")
    private String username;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    private String password;

    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}", message = "올바른 전화번호 형식이 아닙니다. (예: 010-1234-5678)")
    private String phoneNumber;

    @NotBlank(message = "주소는 필수 입력입니다.")
    private String address;

    public MemberRequestDTO() {
    }

}
