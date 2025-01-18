package com.grocery.booking.domian;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groceryItemId;

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroceryItemId() {
        return groceryItemId;
    }

    public void setGroceryItemId(Long groceryItemId) {
        this.groceryItemId = groceryItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", groceryItemId=" + groceryItemId +
                ", quantity=" + quantity +
                '}';
    }
}
