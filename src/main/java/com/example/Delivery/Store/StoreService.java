package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;
import com.example.Delivery.Food.SpringDataJPAFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private final SpringDataJPAStoreRepository springDataJPAStoreRepository;
    private final SpringDataJPAFoodRepository springDataJPAFoodRepository;

    @Autowired
    public StoreService(SpringDataJPAStoreRepository springDataJPAStoreRepository, SpringDataJPAFoodRepository springDataJPAFoodRepository) {
        this.springDataJPAStoreRepository = springDataJPAStoreRepository;
        this.springDataJPAFoodRepository = springDataJPAFoodRepository;
    }

    public Store findStore(Long id) {
        return springDataJPAStoreRepository.findById(id).orElse(null);
    }

    public void saveStore(Store store) {
        springDataJPAStoreRepository.save(store);
        List<Food> menu = store.getMenu();
        if (menu != null) {
            for (Food food : menu) {
                food.setStore(store);
                springDataJPAFoodRepository.save(food);
            }
        }
    }

    public List<Store> findAllStores() {
        return springDataJPAStoreRepository.findAll();
    }

    public void deleteStore(Long id) {
        springDataJPAStoreRepository.deleteById(id);
    }

    public List<Store> findAllStoresByMenu() {
        List<Store> stores = springDataJPAStoreRepository.findAll();
        for (Store store : stores) {
            List<Food> menu = springDataJPAFoodRepository.findByStore_StoreId(store.getStoreId());
            store.setMenu(menu);
        }
        return stores;
    }

    public Store findStoreByMenu(Long id) {
        Store store = springDataJPAStoreRepository.findById(id).orElse(null);
        if (store != null) {
            List<Food> menu = springDataJPAFoodRepository.findByStore_StoreId(id);
            store.setMenu(menu);
        }
        return store;
    }
}
