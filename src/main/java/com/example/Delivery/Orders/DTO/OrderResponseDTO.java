package com.example.Delivery.Orders.DTO;

import com.example.Delivery.Members.DTO.MemberResponseDTO;
import com.example.Delivery.Members.Members;
import com.example.Delivery.Orders.Orders;

// 주문에 대해 member를 처리하는 DTO입니다.

public class OrderResponseDTO {
    private Orders order;
    private MemberResponseDTO member;

    public OrderResponseDTO(Orders order, Members member) {
        this.order = order;
        this.member = new MemberResponseDTO(member.getMemberId(), member.getUsername(), member.getPhoneNumber(), member.getAddress());
    }

    public OrderResponseDTO() {}

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public MemberResponseDTO getMember() {
        return member;
    }

    public void setMember(MemberResponseDTO member) {
        this.member = member;
    }
}
