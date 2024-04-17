package com.example.Delivery.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private FoodRepository foodRepository;

    @Autowired
    FoodService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    public Food findFood(int id) {
        return foodRepository.findFood(id);
    }

    public void saveFood(Food food) {
        foodRepository.save(food);
    }

    public List<Food> findAllFoods() {
        return foodRepository.findAllFoods();
    }

    public void deleteFood(int id) {
        foodRepository.delete(id);
    }

}
