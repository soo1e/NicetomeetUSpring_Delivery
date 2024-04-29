package com.example.Delivery.Orders;

import com.example.Delivery.Members.Members;
import com.example.Delivery.Members.SpringDataJPAMembersRepository;
import com.example.Delivery.Orders.DTO.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    private SpringDataJPAOrdersRepository ordersRepository;
    private SpringDataJPAMembersRepository membersRepository;

    @Autowired
    public OrdersService(SpringDataJPAOrdersRepository ordersRepository, SpringDataJPAMembersRepository membersRepository) {
        this.ordersRepository = ordersRepository;
        this.membersRepository = membersRepository;
    }

    // 전체 주문 조회
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // orderId를 통한 주문 조회
    public Optional<Orders> getOrderById(int orderId) {
        return ordersRepository.findById(orderId);
    }

    // storeID를 통한 주문 조회
    public List<Orders> getOrdersByStoreId(int storeId) {
        return ordersRepository.findByStoreId(storeId);
    }

    // 주문 생성
    public Orders createOrder(OrderRequest orderRequest) {
        // 주문 정보 설정
        Orders order = new Orders();
        order.setStoreId(orderRequest.getStoreId());
        order.setRequest(orderRequest.getRequest());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setOrderAmount(orderRequest.getOrderAmount());
        order.setOrderStatus(orderRequest.getOrderStatus());
        order.setOrderTime(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

        // 회원 ID로 회원을 찾음
        Optional<Members> optionalMember = membersRepository.findById((long) orderRequest.getMemberId());
        if (optionalMember.isPresent()) {
            Members member = optionalMember.get();
            order.setMember(member); // 주문에 회원 정보 추가

            // 주문 정보 저장 후 저장된 주문 객체 반환
            return ordersRepository.save(order);
        } else {
            throw new IllegalArgumentException("해당 memberId에 해당하는 회원을 찾을 수 없습니다.");
        }
    }

    // 주문 정보 수정
    public Orders updateOrder(int orderId, OrderRequest updatedOrderRequest) {
        Optional<Orders> optionalOrder = ordersRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Orders existingOrder = optionalOrder.get();

            // 업데이트할 정보를 OrderRequest로부터 가져와서 설정
            existingOrder.setStoreId(updatedOrderRequest.getStoreId());
            existingOrder.setRequest(updatedOrderRequest.getRequest());
            existingOrder.setPaymentMethod(updatedOrderRequest.getPaymentMethod());
            existingOrder.setOrderAmount(updatedOrderRequest.getOrderAmount());
            existingOrder.setOrderStatus(updatedOrderRequest.getOrderStatus());
            existingOrder.setOrderTime(new Timestamp(System.currentTimeMillis()).toLocalDateTime()); // 현재 시간으로 설정

            // 주문 정보 저장 후 저장된 주문 객체 반환
            return ordersRepository.save(existingOrder);
        } else {
            throw new IllegalArgumentException("해당 id의 주문이 존재하지 않습니다.");
        }
    }


    // 주문 삭제
    public void deleteOrder(int orderId) {
        if (ordersRepository.existsById(orderId)) {
            ordersRepository.deleteById(orderId);
        } else {
            throw new IllegalArgumentException("해당 id의 주문이 존재하지 않습니다.");
        }
    }

}