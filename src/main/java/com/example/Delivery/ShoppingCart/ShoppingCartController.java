package com.example.Delivery.ShoppingCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // 전체 장바구니 조회
    @GetMapping
    public ResponseEntity<?> getAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAllShoppingCarts();
        if (!shoppingCarts.isEmpty()) {
            return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 장바구니가 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // cartId로 장바구니 조회
    @GetMapping("/{cartId}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable("cartId") int cartId) {
        Optional<ShoppingCart> shoppingCart = shoppingCartService.getShoppingCartById(cartId);
        return shoppingCart.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 장바구니 생성
    @PostMapping
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart createdShoppingCart = shoppingCartService.createShoppingCart(shoppingCart);
        return new ResponseEntity<>(createdShoppingCart, HttpStatus.CREATED);
    }

    // 장바구니 수정
    @PutMapping("/{cartId}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable("cartId") int cartId, @RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setCartId(cartId); // 요청된 ID를 사용하여 수정
        ShoppingCart updatedShoppingCart = shoppingCartService.updateShoppingCart(shoppingCart);
        return new ResponseEntity<>(updatedShoppingCart, HttpStatus.OK);
    }

    // 장바구니 삭제
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable("cartId") int cartId) {
        try {
            shoppingCartService.deleteShoppingCart(cartId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("장바구니 삭제가 완료되었습니다");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("장바구니 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
