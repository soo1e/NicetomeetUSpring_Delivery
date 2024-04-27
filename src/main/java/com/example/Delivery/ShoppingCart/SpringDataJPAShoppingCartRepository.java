package com.example.Delivery.ShoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJPAShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
