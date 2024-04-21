package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;
import com.example.Delivery.Food.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private StoreRepository storeRepository;
    private FoodRepository foodRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository, FoodRepository foodRepository) {
        this.storeRepository = storeRepository;
        this.foodRepository = foodRepository;
    }

    public Store findStore(int id) {
        return storeRepository.findById(id).orElse(null);
    }

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

    public List<Store> findAllStores() {
        return storeRepository.findAll();
    }

    public void deleteStore(int id) {
        storeRepository.deleteById(id);
    }

    public List<Store> findAllStoresWithMenu() {
        List<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            List<Food> menu = foodRepository.findByStoreId(store.getId());
            store.setMenu(menu);
        }
        return stores;
    }

    public Store findStoreWithMenu(int id) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store != null) {
            List<Food> menu = foodRepository.findByStoreId(id);
            store.setMenu(menu);
        }
        return store;
    }
}
