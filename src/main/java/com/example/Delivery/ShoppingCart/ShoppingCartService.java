package com.example.Delivery.ShoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final SpringDataJPAShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(SpringDataJPAShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    // 장바구니 생성
    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    // 장바구니 수정
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    // 장바구니 삭제
    public void deleteShoppingCart(int cartId) {
        shoppingCartRepository.deleteById(cartId);
    }

    // cartId로 장바구니 조회
    public Optional<ShoppingCart> getShoppingCartById(int cartId) {
        return shoppingCartRepository.findById(cartId);
    }

    // 전체 장바구니 조회
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }
}
