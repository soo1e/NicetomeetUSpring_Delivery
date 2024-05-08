package com.example.Delivery.Food;

import com.example.Delivery.Food.Error.ErrorMessage;
import com.example.Delivery.Food.Error.NotFoundException;
import com.example.Delivery.Store.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/foods")
@AllArgsConstructor

public class FoodController {

    private final FoodService foodService;
    private final StoreService storeService;

    // 전체 메뉴 조회
    @GetMapping
    public ResponseEntity<?> getAllFood() {
        List<Food> food = foodService.getAllFood();
        if (food.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.MENU_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(food, HttpStatus.OK);
        }
    }

    // 특정 메뉴 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable("id") Long id) {
        try {
            Food food = foodService.getFoodById(id)
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.MENU_NOT_FOUND.getMessage()));
            return new ResponseEntity<>(food, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    // 메뉴 등록
    @PostMapping
    public ResponseEntity<String> saveFood(@RequestBody Food food, @RequestParam("storeId") Long storeId) {
        try {
            foodService.saveFood(food, storeId);
            return new ResponseEntity<>(ErrorMessage.MENU_SAVE_SUCCESS.getMessage(), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(ErrorMessage.FAILED_TO_SAVE_MENU.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 메뉴 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable("id") Long id, @RequestBody Food updatedFood) {
        try {
            Food food = foodService.updateFood(id, updatedFood);
            return new ResponseEntity<>(food, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(ErrorMessage.MENU_UPDATE_FAILED.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // 메뉴 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable("id") Long id) {
        try {
            foodService.deleteFood(id);
            return ResponseEntity.ok(ErrorMessage.MENU_DELETE_SUCCESS.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage());
        }
    }
}