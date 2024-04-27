package com.example.Delivery.Orders;

import com.example.Delivery.Members.Members;
import com.example.Delivery.Members.SpringDataJPAMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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



    // orderId를 통한 주문 조회
    public Optional<Orders> getOrderById(int orderId) {
        return ordersRepository.findById(orderId);
    }

    // storeID를 통한 주문 조회
    public List<Orders> getOrdersByStoreId(int storeId) {
        return ordersRepository.findByStoreId(storeId);
    }

    // 전체 주문 조회
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // 주문 생성
    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    // 주문 삭제
    public void deleteOrder(int orderId) {
        ordersRepository.deleteById(orderId);
    }

    // 주문 정보 수정
    public Orders updateOrder(int orderId, Orders updatedOrder) {
        if (ordersRepository.existsById(orderId)) {
            updatedOrder.setOrderId(orderId);
            return ordersRepository.save(updatedOrder);
        } else {
            throw new IllegalArgumentException("해당 id의 주문이 존재하지 않습니다.");
        }
    }

    public Orders createOrder(int memberId, Orders orderRequest) {
        // 회원 정보 가져오기
        Members member = membersRepository.findById((long) memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 memberId에 해당하는 회원을 찾을 수 없습니다."));

        // 현재 시간 설정
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);


        // 주문 정보 설정
        orderRequest.setMember(member);
        orderRequest.setOrderTime(timestamp);

        // 주문 정보 저장
        return ordersRepository.save(orderRequest);
    }

}