package com.efant.efant.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "efant", catalog = "postgres")
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Basic
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id" , insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @Basic
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
    @JsonBackReference
    private Restaurant restaurant;

    @JsonManagedReference
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @JsonManagedReference
    @OneToOne(mappedBy = "order")
    private OrderStatus orderStatus;


    @Basic
    @Column(name = "order_date")
    private Timestamp orderDate;
    @Basic
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Basic
    @Column(name = "delivery_address_id")
    private Long deliveryAddressId;
    @Basic
    @Column(name = "notes")
    private String notes;

    //Constructors

    public Order() {
    }

    public Order(Long userId, User user, Long restaurantId, Restaurant restaurant, OrderStatus orderStatus, Timestamp orderDate, BigDecimal totalAmount, Long deliveryAddressId, String notes) {
        this.userId = userId;
        this.user = user;
        this.restaurantId = restaurantId;
        this.restaurant = restaurant;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.deliveryAddressId = deliveryAddressId;
        this.notes = notes;
    }

    // Getters and Setters


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    //equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && Objects.equals(userId, order.userId) && Objects.equals(user, order.user) && Objects.equals(restaurantId, order.restaurantId) && Objects.equals(restaurant, order.restaurant) && Objects.equals(orderItems, order.orderItems) && Objects.equals(orderStatus, order.orderStatus) && Objects.equals(orderDate, order.orderDate) && Objects.equals(totalAmount, order.totalAmount) && Objects.equals(deliveryAddressId, order.deliveryAddressId) && Objects.equals(notes, order.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, userId, user, restaurantId, restaurant, orderItems, orderStatus, orderDate, totalAmount, deliveryAddressId, notes);
    }
}
