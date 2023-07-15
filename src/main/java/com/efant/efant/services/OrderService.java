package com.efant.efant.services;

import com.efant.efant.model.entities.Order;
import com.efant.efant.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }


    public Order getOrderById(Long id) throws Exception{
        return orderRepository.findById(id)
                .orElseThrow(() -> new Exception("Order not exists with id: " + id));

    }

    public Order createOrder(Order order){
        order = orderRepository.save(order);
        return order;
    }

    public Order updateOrder(Order order) throws Exception{
        Long ordersId = order.getOrderId();
        Order existingOrder = orderRepository.findById(ordersId)
                .orElseThrow(() -> new Exception("Order not exists with id: " + ordersId));

        existingOrder.setOrderDate(order.getOrderDate());
        existingOrder.setTotalAmount(order.getTotalAmount());
        existingOrder.setNotes(order.getNotes());
        existingOrder.setDeliveryAddressId(order.getDeliveryAddressId());

        existingOrder = orderRepository.save(existingOrder);
        return existingOrder;
    }

    public void deleteOrder(Long id) throws Exception{
        Order order = orderRepository.findById(id).orElse(null);

        if(order != null){
            orderRepository.deleteById(id);
        }
        else{
            throw new Exception("Order not exists with id:" + id);
        }
    }
























}
