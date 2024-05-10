package com.example.Delivery.Store;

import com.example.Delivery.Store.Error.ErrorMessage;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        try {
            List<Store> stores = storeService.findAllStoresByMenu();
            if (stores.isEmpty()) {
                return new ResponseEntity<>(ErrorMessage.STORE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(stores, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 특정 가게 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable("id") Long storeId) {
        try {
            Store store = storeService.findStoreByMenu(storeId);
            if (store != null) {
                return new ResponseEntity<>(store, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ErrorMessage.STORE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 가게 등록
    @PostMapping
    public ResponseEntity<?> addStore(@Valid @RequestBody Store store) {
        try {
            Store savedStore = storeService.saveStore(store);
            return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 특정 가게 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStore(@PathVariable("id") Long storeId, @RequestBody Store storeDetails) {
        try {
            Store existingStore = storeService.findStoreByMenu(storeId);
            if (existingStore != null) {
                storeDetails.setStoreId(storeId);
                Store updatedStore = storeService.updateStore(storeId, storeDetails);
                return new ResponseEntity<>(updatedStore, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ErrorMessage.STORE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(ErrorMessage.STORE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}