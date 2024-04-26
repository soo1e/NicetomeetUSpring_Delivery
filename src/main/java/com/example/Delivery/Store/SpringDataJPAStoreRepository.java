package com.example.Delivery.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJPAStoreRepository extends JpaRepository<Store, Long> {
}