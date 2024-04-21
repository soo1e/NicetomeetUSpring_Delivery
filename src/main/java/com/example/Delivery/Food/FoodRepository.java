package com.example.Delivery.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    // Store ID로 메뉴 조회
    List<Food> findByStoreId(int storeId);

    // 중복 데이터를 제외한 전체 메뉴와 가게 이름 조회
    @Query("SELECT DISTINCT f FROM Food f JOIN FETCH f.store")
    List<Food> findAllDistinctWithStoreName();
}