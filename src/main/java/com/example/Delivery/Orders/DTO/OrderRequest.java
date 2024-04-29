package com.example.Delivery.Orders.DTO;

import com.example.Delivery.Orders.Orders;

import java.sql.Timestamp;

// 주문 요청에 따라 정보를 처리하는 DTO입니다.
public class OrderRequest {
    private int storeId;
    private String request;
    private String paymentMethod;
    private long orderAmount;
    private Orders.OrderStatus orderStatus;
    private Long memberId;
    private Timestamp orderTime;

    public OrderRequest() {}

    public OrderRequest(int storeId, String request, String paymentMethod, long orderAmount, Orders.OrderStatus orderStatus, Long memberId, Timestamp orderTime) {
        this.storeId = storeId;
        this.request = request;
        this.paymentMethod = paymentMethod;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
        this.memberId = memberId;
        this.orderTime = new Timestamp(System.currentTimeMillis()); // 현재 시간으로 설정
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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

    public Orders.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Orders.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }
}