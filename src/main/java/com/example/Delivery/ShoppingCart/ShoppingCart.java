package com.example.Delivery.ShoppingCart;

import com.example.Delivery.Members.Members;
import com.example.Delivery.Food.Food;
import jakarta.persistence.*;


@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members member;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Food menu;

    private int quantity;
    private int price;

    public ShoppingCart() {
    }

    public ShoppingCart(Members member, Food menu, int quantity, int price) {
        this.member = member;
        this.menu = menu;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Members getMember() {
        return member;
    }

    public void setMember(Members member) {
        this.member = member;
    }

    public Food getMenu() {
        return menu;
    }

    public void setMenu(Food menu) {
        this.menu = menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
