package com.example.Delivery.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // 전체 가게 조회
    @GetMapping
    public ResponseEntity<List<Store>> getAllStoresWithMenu() {
        List<Store> stores = storeService.findAllStoresWithMenu();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }


    // 특정 가게 조회
    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable("id") Long storeId) {
        Store store = storeService.findStoreWithMenu(storeId);
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
    public ResponseEntity<Void> deleteStore(@PathVariable("id") Long storeId) {
        Store existingStore = storeService.findStore(storeId);
        if (existingStore != null) {
            storeService.deleteStore(storeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

