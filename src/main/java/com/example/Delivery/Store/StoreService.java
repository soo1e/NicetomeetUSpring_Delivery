package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private static StoreRepository storeRepository;

    @Autowired
    StoreService(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    public Store findStore(int id) {
        return storeRepository.findStore(id);
    }

    public static void saveStore(Store store) {
        storeRepository.save(store);
    }

    public List<Store> findAllStores() {
        return storeRepository.findAllStores();
    }

    public void deleteStore(int id) {
        storeRepository.delete(id);
    }

}
