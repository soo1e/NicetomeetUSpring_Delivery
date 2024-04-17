package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class StoreController {

    @Autowired
    private StoreService storeService;

    // 음식 조회
    @RequestMapping(value = "/stores/{id}", method = RequestMethod.GET)
    public Store findStore(@PathVariable("id") int id) {
        System.out.println(id);
        return storeService.findStore(id);
    }

    // 전체 메뉴 조회
    @GetMapping("/stores")
    public List<Store> findAllStores() {
        return storeService.findAllStores();
    }


    // 메뉴 등록
    @RequestMapping(value = "/stores", method = RequestMethod.POST)
    public void saveStores(@RequestBody Store store) {
        System.out.println("POST");
        StoreService.saveStore(store);
    }

    // 메뉴 삭제
    @DeleteMapping("/stores/{id}")
    public void deleteStore(@PathVariable("id") int id) {
        storeService.deleteStore(id);
    }
}
