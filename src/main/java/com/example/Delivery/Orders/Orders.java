package com.example.Delivery.Orders;

import com.example.Delivery.Members.Members;
import com.example.Delivery.TimeConverter.LocalDateTimeAttributeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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
}
