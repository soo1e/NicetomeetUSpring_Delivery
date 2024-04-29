package com.example.Delivery.Orders.DTO;

import com.example.Delivery.Members.DTO.MemberResponse;
import com.example.Delivery.Members.Members;
import com.example.Delivery.Orders.Orders;

// 주문에 대해 member를 처리하는 DTO입니다.
public class OrderResponse {
    private Orders order;
    private MemberResponse member;

    public OrderResponse(Orders order, Members member) {
        this.order = order;
        this.member = new MemberResponse(member.getMemberId(), member.getUsername(), member.getPhoneNumber(), member.getAddress());
    }

    public OrderResponse() {}

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public MemberResponse getMember() {
        return member;
    }

    public void setMember(MemberResponse member) {
        this.member = member;
    }
}
