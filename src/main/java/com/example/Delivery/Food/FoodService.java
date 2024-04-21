package com.example.Delivery.Food;

import com.example.Delivery.Store.Store;

import com.example.Delivery.Store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository, StoreRepository storeRepository) {
        this.foodRepository = foodRepository;
        this.storeRepository = storeRepository;
    }

    public Food findFood(int id) {
        return foodRepository.findById((long) id).orElse(null);
    }

    public void saveFood(Food food, int storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        food.setStore(store);
        foodRepository.save(food);
    }


    public List<Food> findAllFoods() {
        return foodRepository.findAllDistinctWithStoreName();
    }

    public void deleteFood(int id) {
        foodRepository.deleteById((long) id);
    }

    public List<Food> findMenuByStoreId(int storeId) {
        return foodRepository.findByStoreId(storeId);
    }

}
