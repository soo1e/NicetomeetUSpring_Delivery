package com.example.Delivery.Members;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJPAMembersRepository extends JpaRepository<Members, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
