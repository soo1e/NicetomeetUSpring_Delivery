package com.example.Delivery.Food;

import com.example.Delivery.Store.Store;
import com.example.Delivery.Store.StoreController;
import com.example.Delivery.Store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foods")
public class FoodController {

    private final FoodService foodService;
    private final StoreService storeService;

    @Autowired
    public FoodController(FoodService foodService, StoreService storeService) {
        this.foodService = foodService;
        this.storeService = storeService;
    }



    // 전체 메뉴 조회
    @GetMapping
    public ResponseEntity<List<Food>> getAllFood() {
        List<Food> foodList = foodService.getAllFood();
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    // 특정 메뉴 조회
    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable("id") Long id) {
        Optional<Food> food = foodService.getFoodById(id);
        return food.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 메뉴 추가
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> saveFood(@RequestBody Food food, @RequestParam("storeId") Long storeId) {
        try {
            foodService.saveFood(food, storeId);
            return new ResponseEntity<>("메뉴를 성공적으로 등록했습니다.", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




    // 메뉴 수정
    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable("id") Long id, @RequestBody Food updatedFood) {
        Food modifiedFood = foodService.updateFood(id, updatedFood);
        return new ResponseEntity<>(modifiedFood, HttpStatus.OK);
    }

    // 메뉴 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable("id") Long id) {
        foodService.deleteFood(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
