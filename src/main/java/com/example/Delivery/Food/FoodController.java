package com.example.Delivery.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class FoodController {

    @Autowired
    private FoodService foodService;

    // 잘되는지 테스트
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String mainFood() {
        return "Hello!";
    }

    // 음식 조회
    @RequestMapping(value = "/foods/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findFood(@PathVariable("id") int id) {
        Food food = foodService.findFood(id);
        if (food != null) {
            return new ResponseEntity<>(food, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 음식이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 전체 메뉴 조회
    @GetMapping("/foods")
    public ResponseEntity<?> findAllFoods() {
        List<Food> foods = foodService.findAllFoods();
        if (!foods.isEmpty()) {
            return new ResponseEntity<>(foods, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 메뉴가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 메뉴 등록
    @RequestMapping(value = "/foods", method = RequestMethod.POST)
    public ResponseEntity<String> saveFood(@RequestBody Food food, @RequestParam("storeId") int storeId) {
        try {
            foodService.saveFood(food, storeId);
            return new ResponseEntity<>("메뉴를 성공적으로 등록했습니다.", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 메뉴 삭제
    @DeleteMapping("/foods/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable("id") int id) {
        try {
            foodService.deleteFood(id);
            return new ResponseEntity<>("메뉴 삭제에 성공했습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

