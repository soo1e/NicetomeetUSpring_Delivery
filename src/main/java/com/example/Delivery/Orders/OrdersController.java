package com.example.Delivery.Orders;

import com.example.Delivery.Members.Members;
import com.example.Delivery.Members.SpringDataJPAMembersRepository;
import com.example.Delivery.Orders.DTO.OrderRequestDTO;
import com.example.Delivery.Orders.DTO.OrderResponseDTO;
import com.example.Delivery.Orders.Error.ErrorMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    private SpringDataJPAMembersRepository membersRepository;

    // 전체 주문 조회
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<Orders> orders = ordersService.getAllOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.ORDER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

    // orderId를 통한 주문 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable("orderId") int orderId) {
        Optional<Orders> orders = ordersService.getOrderById(orderId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.ORDER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

    // storeId를 통한 주문 조회
    @GetMapping("/store/{storeId}")
    public ResponseEntity<?> getOrdersByStoreId(@PathVariable("storeId") int storeId) {
        List<Orders> orders = ordersService.getOrdersByStoreId(storeId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(ErrorMessage.ORDER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

    // 주문 등록
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        // 주문 정보 설정
        Orders order = new Orders();
        order.setStoreId(orderRequestDTO.getStoreId());
        order.setRequest(orderRequestDTO.getRequest());
        order.setPaymentMethod(orderRequestDTO.getPaymentMethod());
        order.setOrderAmount(orderRequestDTO.getOrderAmount());
        order.setOrderStatus(orderRequestDTO.getOrderStatus());
        order.setOrderTime(new Timestamp(System.currentTimeMillis()).toLocalDateTime()); // 현재 시간으로

        // 회원 ID로 회원을 찾음
        Members member = membersRepository.findById(orderRequestDTO.getMemberId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.MEMBER_NOT_FOUND.getMessage()));

        order.setMember(member); // 주문에 회원 정보 추가

        // 주문 정보 저장 후 저장된 주문 객체 반환
        Orders savedOrder = ordersService.createOrder(orderRequestDTO); // OrderRequest를 사용하여 주문을 생성

        // 회원 정보와 함께 OrderResponse 객체를 생성하여 반환
        OrderResponseDTO response = new OrderResponseDTO(savedOrder, member);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 주문 수정
    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable("orderId") int orderId, @RequestBody OrderRequestDTO updatedOrderRequestDTO) {
        // 주문 정보 가져오기
        Orders order = ordersService.getOrderById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND.getMessage()));

        // OrderRequest를 사용하여 주문 정보 업데이트
        Orders updatedOrder = ordersService.updateOrder(orderId, updatedOrderRequestDTO);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }


    // 주문 삭제
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") int orderId) {
        try {
            ordersService.deleteOrder(orderId);
            return ResponseEntity.ok(ErrorMessage.ORDER_DELETE_SUCCESS.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessage.ORDER_DELETE_FAILED.getMessage() + e.getMessage());
        }
    }
}
