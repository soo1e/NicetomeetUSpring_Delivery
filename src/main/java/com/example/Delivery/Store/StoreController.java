package com.example.Delivery.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
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
    public ResponseEntity<?> getAllStoresByMenu() {
        List<Store> storeList = storeService.findAllStoresByMenu();
        if (!storeList.isEmpty()) {
            return new ResponseEntity<>(storeList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 가게가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 특정 가게 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable("id") Long storeId) {
        Store store = storeService.findStoreByMenu(storeId);
        if (store != null) {
            return new ResponseEntity<>(store, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 가게가 없습니다." ,HttpStatus.NOT_FOUND);
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
                return new ResponseEntity<>("해당 ID의 가게를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("해당 ID의 가게를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
    }


    // 특정 가게 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable("id") Long storeId) {
        try {
            storeService.deleteStore(storeId);
            return ResponseEntity.ok("가게 삭제가 완료되었습니다");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 가게를 찾을 수 없습니다");
        }
    }

}

