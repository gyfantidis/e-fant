package com.efant.efant.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "order_status", schema = "efant", catalog = "postgres")
public class OrderStatus {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_status_id")
    private long orderStatusId;
    @Basic
    @Column(name = "order_id")
    private Long orderId;

    @OneToOne
    @JoinColumn(name = "order_id" , insertable = false, updatable = false)
    @JsonBackReference
    private Order order;

    @Basic
    @Column(name = "received")
    private Boolean received;
    @Basic
    @Column(name = "processing")
    private Boolean processing;
    @Basic
    @Column(name = "on_the_road")
    private Boolean onTheRoad;
    @Basic
    @Column(name = "delivered")
    private Boolean delivered;
    @Basic
    @Column(name = "delivery_date")
    private Timestamp deliveryDate;
    @Basic
    @Column(name = "status")
    private String status;


    // Constructors


    public OrderStatus() {
    }

    public OrderStatus(Long orderId, Boolean received, Boolean processing, Boolean onTheRoad, Boolean delivered, Timestamp deliveryDate, String status) {
        this.orderId = orderId;
        this.received = received;
        this.processing = processing;
        this.onTheRoad = onTheRoad;
        this.delivered = delivered;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }


    // Getters and Setters


    public long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;
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

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public Boolean getProcessing() {
        return processing;
    }

    public void setProcessing(Boolean processing) {
        this.processing = processing;
    }

    public Boolean getOnTheRoad() {
        return onTheRoad;
    }

    public void setOnTheRoad(Boolean onTheRoad) {
        this.onTheRoad = onTheRoad;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    // equals and hashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatus that = (OrderStatus) o;
        return orderStatusId == that.orderStatusId && Objects.equals(orderId, that.orderId) && Objects.equals(order, that.order) && Objects.equals(received, that.received) && Objects.equals(processing, that.processing) && Objects.equals(onTheRoad, that.onTheRoad) && Objects.equals(delivered, that.delivered) && Objects.equals(deliveryDate, that.deliveryDate) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderStatusId, orderId, order, received, processing, onTheRoad, delivered, deliveryDate, status);
    }
}
