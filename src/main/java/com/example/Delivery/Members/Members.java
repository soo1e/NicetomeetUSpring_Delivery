package com.example.Delivery.Members;

import com.example.Delivery.TimeConverter.LocalDateTimeAttributeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Members {

    public enum Role {
        MEMBER, SHOP_OWNER, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;


    @NotBlank(message = "사용자 이름은 필수 입력입니다.")
    @Size(min = 3, max = 50, message = "사용자 이름은 3자 이상 50자 이하여야 합니다.")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "이메일은 필수 입력입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "ENUM('MEMBER', 'SHOP_OWNER', 'ADMIN') DEFAULT 'MEMBER'")
    private Role role;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

    public Members() {
    }

    public Members(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
