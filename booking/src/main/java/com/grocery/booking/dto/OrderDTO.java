package com.grocery.booking.dto;

import com.grocery.booking.domian.OrderItem;

import java.util.List;

public class OrderDTO {

    private Long id;

    private String user;

    private List<OrderItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "user='" + user +
                ", items=" + items +
                '}';
    }
}
