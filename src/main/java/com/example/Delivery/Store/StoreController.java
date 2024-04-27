package com.example.Delivery.Store;

import com.example.Delivery.Food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }


    // 전체 가게 조회
    @GetMapping
    public ResponseEntity<List<Store>> getAllStoresByMenu() {
        List<Store> stores = storeService.findAllStoresByMenu();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }


    // 특정 가게 조회
    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable("id") Long storeId) {
        Store store = storeService.findStoreByMenu(storeId);
        if (store != null) {
            return new ResponseEntity<>(store, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 가게 등록
    @PostMapping
    public ResponseEntity<Store> addStore(@RequestBody Store store) {
        storeService.saveStore(store);
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    // 특정 가게 수정
    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable("id") Long storeId, @RequestBody Store storeDetails) {
        Store existingStore = storeService.findStore(storeId);
        if (existingStore != null) {
            storeDetails.setStoreId(storeId);
            storeService.saveStore(storeDetails);
            return new ResponseEntity<>(storeDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 특정 가게 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable("id") Long storeId) {
        Store existingStore = storeService.findStore(storeId);
        if (existingStore != null) {
            storeService.deleteStore(storeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("가게 삭제가 완료되었습니다");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 가게를 찾을 수 없습니다");
        }
    }
}

