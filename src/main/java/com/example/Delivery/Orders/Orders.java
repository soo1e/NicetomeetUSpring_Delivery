package com.example.Delivery.Orders;

import com.example.Delivery.Members.Members;
import com.example.Delivery.TimeConverter.LocalDateTimeAttributeConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {

    public enum OrderStatus {
        ORDER_CONFIRMED,
        COOKING,
        ON_DELIVERY,
        DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members member;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "request")
    private String request;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "order_amount")
    private long orderAmount;

    @CreationTimestamp
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    public Orders() {
    }

    public Orders(int orderId, Members member, int storeId, String request, String paymentMethod, long orderAmount, Timestamp orderTime, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.member = member;
        this.storeId = storeId;
        this.request = request;
        this.paymentMethod = paymentMethod;
        this.orderAmount = orderAmount;
        this.orderTime = orderTime.toLocalDateTime();
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Long getMemberId() {
        return member != null ? member.getMemberId() : null;
    }

    public void setMember(Members member) {
        this.member = member;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
