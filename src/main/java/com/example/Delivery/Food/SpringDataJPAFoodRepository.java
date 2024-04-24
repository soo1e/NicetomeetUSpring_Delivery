package com.example.Delivery.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJPAFoodRepository extends JpaRepository<Food, Long> {
    // 중복 데이터를 제외한 전체 메뉴와 가게 이름 조회
    @Query("SELECT DISTINCT f FROM Food f JOIN FETCH f.store")
    List<Food> findAllDistinctWithStoreName();

    List<Food> findByStore_StoreId(Long storeId);
}
