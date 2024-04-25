package com.example.Delivery.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.time.LocalDateTime;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // 주문 생성
    // 주문 생성
    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        // orderTime을 Timestamp로 변환
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        order.setOrderTime(timestamp);

        Orders createdOrder = ordersService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }



    // orderId를 통한 주문 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable("orderId") int orderId) {
        Optional<Orders> order = ordersService.getOrderById(orderId);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // storeId를 통한 주문 조회
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Orders>> getOrdersByStoreId(@PathVariable("storeId") int storeId) {
        List<Orders> orders = ordersService.getOrdersByStoreId(storeId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // 전체 주문 조회
    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // 주문 삭제
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") int orderId) {
        ordersService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 주문 정보 수정
    @PutMapping("/{orderId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable("orderId") int orderId, @RequestBody Orders updatedOrder) {
        Orders order = ordersService.updateOrder(orderId, updatedOrder);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
