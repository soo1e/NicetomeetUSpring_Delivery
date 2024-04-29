package com.example.Delivery.Food;

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
    public ResponseEntity<?> getAllFood() {
        List<Food> foodList = foodService.getAllFood();
        if (!foodList.isEmpty()) {
            return new ResponseEntity<>(foodList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 메뉴가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 특정 메뉴 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable("id") Long id) {
        Optional<Food> food = foodService.getFoodById(id);
        if (food.isPresent()) {
            return new ResponseEntity<>(food, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 메뉴가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 메뉴 등록
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
    public ResponseEntity<?> updateFood(@PathVariable("id") Long id, @RequestBody Food updatedFood) {
        Food food = foodService.updateFood(id, updatedFood);
        if (food != null) {
            return new ResponseEntity<>(food, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("메뉴 수정에 실패했습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 메뉴 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable("id") Long id) {
        try {
            foodService.deleteFood(id);
            return ResponseEntity.ok("메뉴 삭제가 완료되었습니다");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메뉴 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}