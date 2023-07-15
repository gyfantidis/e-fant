package com.efant.efant.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "order_items", schema = "efant", catalog = "postgres")
public class OrderItem {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_item")
    private long orderItem;
    @Basic
    @Column(name = "item_id")
    private Long itemId;

    @OneToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private MenuItem menuItem;


    @Basic
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @JsonBackReference
    private Order order;


    @Basic
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "notes")
    private String notes;


    //Constructors
    public OrderItem() {
    }

    public OrderItem(Long itemId, Long orderId, Order order, Integer quantity, String notes) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.order = order;
        this.quantity = quantity;
        this.notes = notes;
    }

    // Getters and Setters

    public long getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(long orderItem) {
        this.orderItem = orderItem;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // equals and hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem1 = (OrderItem) o;
        return orderItem == orderItem1.orderItem && Objects.equals(itemId, orderItem1.itemId) && Objects.equals(menuItem, orderItem1.menuItem) && Objects.equals(orderId, orderItem1.orderId) && Objects.equals(order, orderItem1.order) && Objects.equals(quantity, orderItem1.quantity) && Objects.equals(notes, orderItem1.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItem, itemId, menuItem, orderId, order, quantity, notes);
    }
}
