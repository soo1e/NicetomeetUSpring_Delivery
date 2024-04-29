package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;
import com.example.Delivery.Food.SpringDataJPAFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StoreService {

    private final SpringDataJPAStoreRepository storeRepository;
    private final SpringDataJPAFoodRepository foodRepository;

    @Autowired
    public StoreService(SpringDataJPAStoreRepository storeRepository, SpringDataJPAFoodRepository foodRepository) {
        this.storeRepository = storeRepository;
        this.foodRepository = foodRepository;
    }

    // 전체 가게 조회
    public List<Store> findAllStoresByMenu() {
        List<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            List<Food> menu = foodRepository.findByStore_StoreId(store.getStoreId());
            store.setMenu(menu);
        }
        return stores;
    }

    // 특정 가게 조회
    public Optional<Store> findStore(Long storeId) {
        return storeRepository.findById(storeId);
    }

    // id로 특정 가게 조회
    public Store findStoreByMenu(Long storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store != null) {
            List<Food> menu = foodRepository.findByStore_StoreId(storeId);
            store.setMenu(menu);
        }
        return store;
    }

    // 가게 저장
    public void saveStore(Store store) {
        storeRepository.save(store);
        List<Food> menu = store.getMenu();
        if (menu != null) {
            for (Food food : menu) {
                food.setStore(store);
                foodRepository.save(food);
            }
        }
    }

    // 가게 수정
    public Store updateStore(Long storeId, Store updatedStore) {
        if (storeRepository.existsById(storeId)) {
            updatedStore.setStoreId(storeId);
            return storeRepository.save(updatedStore);
        } else {
            throw new NoSuchElementException("해당 id의 가게가 존재하지 않습니다.");
        }
    }

    // 가게 삭제
    public void deleteStore(Long storeId) {
        if (storeRepository.existsById(storeId)) {
            storeRepository.deleteById(storeId);
        } else {
            throw new NoSuchElementException("해당 id의 가게가 존재하지 않습니다.");
        }
    }
}
