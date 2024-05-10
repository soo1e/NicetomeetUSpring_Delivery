package com.example.Delivery.Store;

import com.example.Delivery.Food.Food;
import com.example.Delivery.Food.SpringDataJPAFoodRepository;
import com.example.Delivery.Review.Review;
import com.example.Delivery.Review.SpringDataJPAReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class StoreService {

    private final SpringDataJPAStoreRepository storeRepository;
    private final SpringDataJPAFoodRepository foodRepository;
    private final SpringDataJPAReviewRepository reviewRepository;

    // 전체 가게 조회
    public List<Store> findAllStoresByMenu() {
        List<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            List<Food> menu = foodRepository.findByStore_StoreId(store.getStoreId());
            store.setMenu(menu);
            calculateAverageRating(store);
        }
        return stores;
    }

    // 특정 가게 조회
    public Store findStoreByMenu(Long storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);
        if (store != null) {
            List<Food> menu = foodRepository.findByStore_StoreId(storeId);
            store.setMenu(menu);
            calculateAverageRating(store);
        }
        return store;
    }

    // 가게 저장
    public Store saveStore(Store store) {
        store.calculateAverageRating(reviewRepository.findByStoreId(store.getStoreId()));
        return storeRepository.save(store);
    }


    // 가게 수정
    public Store updateStore(Long storeId, Store updatedStore) {
        if (storeRepository.existsById(storeId)) {
            updatedStore.setStoreId(storeId);
            updatedStore.calculateAverageRating(reviewRepository.findByStoreId(storeId));
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

    // 리뷰들의 평균 rating을 계산하는 메서드
    private void calculateAverageRating(Store store) {
        List<Review> reviews = reviewRepository.findByStoreId(store.getStoreId());
        if (reviews != null && !reviews.isEmpty()) {
            double sum = 0.0;
            for (Review review : reviews) {
                sum += review.getRating();
            }
            double averageRating = sum / reviews.size();
            store.setAverageRating(averageRating);
        } else {
            store.setAverageRating(null);
        }
    }
}
