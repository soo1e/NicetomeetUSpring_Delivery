package com.example.Delivery.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJPAStoreRepository extends JpaRepository<Store, Long> {
    // 추가적인 메소드가 필요하다면 여기에 선언할 수 있습니다.
}