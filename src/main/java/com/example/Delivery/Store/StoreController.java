package com.example.Delivery.Store;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Delivery.Store.Error.ErrorMessage;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/stores")
@AllArgsConstructor
public class StoreController {

    private StoreService storeService;


    // 전체 가게 조회
    @GetMapping
    public ResponseEntity<?> getAllStoresByMenu() {
        List<Store> store = storeService.findAllStoresByMenu();
        if (store.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.STORE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(store, HttpStatus.OK);
        }
    }

    // 특정 가게 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable("id") Long storeId) {
        Store store = storeService.findStoreByMenu(storeId);
        if (store != null) {
            return new ResponseEntity<>(store, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.STORE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
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
    public ResponseEntity<?> updateStore(@PathVariable("id") Long storeId, @RequestBody Store storeDetails) {
        try {
            Store existingStore = storeService.findStoreByMenu(storeId);
            if (existingStore != null) {
                storeDetails.setStoreId(storeId);
                storeService.saveStore(storeDetails);
                return new ResponseEntity<>(storeDetails, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ErrorMessage.STORE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(ErrorMessage.STORE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    // 특정 가게 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable("id") Long storeId) {
        try {
            storeService.deleteStore(storeId);
            return ResponseEntity.ok(ErrorMessage.STORE_DELETE_SUCCESS.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.STORE_NOT_FOUND.getMessage());
        }
    }

}