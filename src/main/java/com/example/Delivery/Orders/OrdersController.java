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

    // 전체 주문 조회
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<Orders> ordersList = ordersService.getAllOrders();
        if (!ordersList.isEmpty()) {
            return new ResponseEntity<>(ordersList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 주문이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // orderId를 통한 주문 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable("orderId") int orderId) {
        Optional<Orders> orders = ordersService.getOrderById(orderId);
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 주문이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // storeId를 통한 주문 조회
    @GetMapping("/store/{storeId}")
    public ResponseEntity<?> getOrdersByStoreId(@PathVariable("storeId") int storeId) {
        List<Orders> orders = ordersService.getOrdersByStoreId(storeId);
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("조회할 주문이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 주문 등록
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Orders order) {
        // orderTime을 Timestamp로 변환
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);

        order.setOrderTime(timestamp);

        try {
            ordersService.createOrder(order);
            return new ResponseEntity<>("주문을 성공적으로 등록했습니다.", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
//        Orders createdOrder = ordersService.createOrder(order);
//        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    // 주문 정보 수정
    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable("orderId") int orderId, @RequestBody Orders updatedOrder) {
        Orders orders = ordersService.updateOrder(orderId, updatedOrder);
        if (orders != null) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("주문 수정에 실패했습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 주문 삭제
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") int orderId) {
        ordersService.deleteOrder(orderId);
        return new ResponseEntity<>("주문 삭제를 성공했습니다.", HttpStatus.NO_CONTENT);
        //        try {
//            ordersService.deleteOrder(orderId);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("주문 삭제가 완료되었습니다");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 삭제 중 오류가 발생했습니다: " + e.getMessage());
//        }
    }
}
