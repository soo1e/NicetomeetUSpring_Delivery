package com.example.Delivery.Food;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Food findFood(@PathVariable("id") int id) {
        System.out.println(id);
        return foodService.findFood(id);
    }

    // 전체 메뉴 조회
    @GetMapping("/foods")
    public List<Food> findAllFoods() {
        return foodService.findAllFoods();
    }


    // 메뉴 등록
    @RequestMapping(value = "/foods", method = RequestMethod.POST)
    public void saveProduct(@RequestBody Food food) {
        System.out.println("POST");
        foodService.saveFood(food);
    }

    // 메뉴 삭제
    @DeleteMapping("/foods/{id}")
    public void deleteFood(@PathVariable("id") int id) {
        foodService.deleteFood(id);
    }
}
