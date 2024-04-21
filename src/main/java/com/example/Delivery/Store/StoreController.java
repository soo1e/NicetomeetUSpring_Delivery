package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class StoreController {

    @Autowired
    private StoreService storeService;

    // 가게 조회
    @RequestMapping(value = "/stores/{id}", method = RequestMethod.GET)
    public Store findStore(@PathVariable("id") int id) {
        return storeService.findStoreWithMenu(id);
    }

    // 전체 가게 조회
    @GetMapping("/stores")
    public List<Store> findAllStores() {
        return storeService.findAllStoresWithMenu();
    }


    // 가게 등록
    @PostMapping(value = "/stores", consumes = {"application/json"})
    public ResponseEntity<String> saveStores(@RequestBody Store store) {
        try {
            storeService.saveStore(store);
            return new ResponseEntity<>("Store saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 가게 삭제
    @DeleteMapping("/stores/{id}")
    public void deleteStore(@PathVariable("id") int id) {
        storeService.deleteStore(id);
    }



}
