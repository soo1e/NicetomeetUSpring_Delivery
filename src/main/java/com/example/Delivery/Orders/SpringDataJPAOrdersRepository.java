package com.example.Delivery.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataJPAOrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByStoreId(int storeId);
}
