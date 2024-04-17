package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StoreRepository {
    private Map<Integer, Store> db = new HashMap<>();
    private int id = 1;

    // 가게 조회
    public Store findStore(int idx) {
        return db.get(idx);
    }

    // 모든 가게 조회
    public List<Store> findAllStores() {
        // map의 value들만 모아서 arrayList로 만들기!
        return new ArrayList<>(db.values());
    }

    // 가게 등록
    public void save (Store store) {
        db.put(id++, store);
        System.out.println(store.getName());
    }

    // 가게 삭제
    public void delete(int id) {
        db.remove(id);
    }
}
