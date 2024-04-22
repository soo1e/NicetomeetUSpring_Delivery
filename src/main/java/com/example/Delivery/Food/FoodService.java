package com.example.Delivery.Food;

import com.example.Delivery.Store.Store;

import com.example.Delivery.Store.SpringDataJPAStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final SpringDataJPAFoodRepository springDataJPAFoodRepository;
    private final SpringDataJPAStoreRepository springDataJPAStoreRepository;

    @Autowired
    public FoodService(SpringDataJPAFoodRepository springDataJPAFoodRepository, SpringDataJPAStoreRepository springDataJPAStoreRepository) {
        this.springDataJPAFoodRepository = springDataJPAFoodRepository;
        this.springDataJPAStoreRepository = springDataJPAStoreRepository;
    }

    public Food findFood(int id) {
        return springDataJPAFoodRepository.findById((long) id).orElse(null);
    }

    public void saveFood(Food food, int storeId) {
        Store store = springDataJPAStoreRepository.findById(storeId).orElseThrow(() -> new RuntimeException("가게를 찾을 수 없습니다."));
        food.setStore(store);
        springDataJPAFoodRepository.save(food);
    }


    public List<Food> findAllFoods() {
        return springDataJPAFoodRepository.findAllDistinctWithStoreName();
    }

    public void deleteFood(int id) {
        springDataJPAFoodRepository.deleteById((long) id);
    }

    public List<Food> findMenuByStoreId(int storeId) {
        return springDataJPAFoodRepository.findByStoreId(storeId);
    }

}
