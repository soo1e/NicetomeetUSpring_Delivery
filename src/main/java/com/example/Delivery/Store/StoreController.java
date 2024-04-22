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
    public ResponseEntity<?> findStore(@PathVariable("id") int id) {
        try {
            Store store = storeService.findStoreWithMenu(id);
            return new ResponseEntity<>(store, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("가게 조회에 실패했습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 전체 가게 조회
    @GetMapping("/stores")
    public ResponseEntity<?> findAllStores() {
        try {
            List<Store> stores = storeService.findAllStoresWithMenu();
            return new ResponseEntity<>(stores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("가게 조회에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 가게 등록
    @PostMapping(value = "/stores", consumes = {"application/json"})
    public ResponseEntity<String> saveStores(@RequestBody Store store) {
        try {
            storeService.saveStore(store);
            return new ResponseEntity<>("가게 등록에 성공했습니다.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("가게 등록에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    // 가게 삭제
    @DeleteMapping("/stores/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable("id") int id) {
        try {
            storeService.deleteStore(id);
            return new ResponseEntity<>("가게 삭제에 성공했습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
