package com.example.Delivery.Orders;

import com.example.Delivery.Members.Members;
import com.example.Delivery.Members.SpringDataJPAMembersRepository;
import com.example.Delivery.Orders.DTO.OrderRequest;
import com.example.Delivery.Orders.DTO.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private SpringDataJPAMembersRepository membersRepository;

    @Autowired
    public OrdersController(OrdersService ordersService, SpringDataJPAMembersRepository membersRepository) {
        this.ordersService = ordersService;
        this.membersRepository = membersRepository;
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
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        // 주문 정보 설정
        Orders order = new Orders();
        order.setStoreId(orderRequest.getStoreId());
        order.setRequest(orderRequest.getRequest());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setOrderAmount(orderRequest.getOrderAmount());
        order.setOrderStatus(orderRequest.getOrderStatus());
        order.setOrderTime(new Timestamp(System.currentTimeMillis()).toLocalDateTime()); // 현재 시간으로 설정

        // 회원 ID로 회원을 찾음
        Optional<Members> optionalMember = membersRepository.findById(orderRequest.getMemberId());
        if (optionalMember.isPresent()) {
            Members member = optionalMember.get();
            order.setMember(member); // 주문에 회원 정보 추가

            // 주문 정보 저장 후 저장된 주문 객체 반환
            Orders savedOrder = ordersService.createOrder(orderRequest); // OrderRequest를 사용하여 주문을 생성

            // 회원 정보와 함께 OrderResponse 객체를 생성하여 반환
            OrderResponse response = new OrderResponse(savedOrder, member);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("해당 memberId에 해당하는 회원을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
    }


    // 주문 수정
    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable("orderId") int orderId, @RequestBody OrderRequest updatedOrderRequest) {
        // 주문 정보 가져오기
        Optional<Orders> optionalOrder = ordersService.getOrderById(orderId);
        if (optionalOrder.isPresent()) {
            // OrderRequest를 사용하여 주문 정보 업데이트
            Orders updatedOrder = ordersService.updateOrder(orderId, updatedOrderRequest);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("수정할 주문을 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    // 주문 삭제
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") int orderId) {
        try {
            ordersService.deleteOrder(orderId);
            return ResponseEntity.ok("주문 삭제가 완료되었습니다");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
