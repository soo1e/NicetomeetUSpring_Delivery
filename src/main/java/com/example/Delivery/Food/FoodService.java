package com.example.Delivery.Food;

import com.example.Delivery.Store.SpringDataJPAStoreRepository;
import com.example.Delivery.Store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final SpringDataJPAFoodRepository foodRepository;
    private final SpringDataJPAStoreRepository springDataJPAStoreRepository;


    @Autowired
    public FoodService(SpringDataJPAFoodRepository foodRepository, SpringDataJPAStoreRepository springDataJPAStoreRepository) {
        this.foodRepository = foodRepository;
        this.springDataJPAStoreRepository = springDataJPAStoreRepository;
    }

    // 전체 메뉴 조회
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    // 특정 메뉴 조회
    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    // 메뉴 수정
    public Food updateFood(Long id, Food updatedFood) {
        if (foodRepository.existsById(id)) {
            updatedFood.setFoodId(id);
            return foodRepository.save(updatedFood);
        } else {
            throw new IllegalArgumentException("해당 id의 메뉴가 존재하지 않습니다.");
        }
    }

    // 메뉴 삭제
    public void deleteFood(Long id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("해당 id의 메뉴가 존재하지 않습니다.");
        }
    }

    // 메뉴 저장
    public void saveFood(Food food, Long storeId) {
        Store store = springDataJPAStoreRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        food.setStore(store);
        foodRepository.save(food);
    }

}