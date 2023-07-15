package com.efant.efant.services;

import com.efant.efant.model.entities.OrderItem;
import com.efant.efant.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsService {

    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemsService(OrderItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getAllOrderItems(){
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Long id) throws Exception{
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new Exception("Item not exists with id: " + id));

    }

    public OrderItem createOrderItem(OrderItem orderItem){
        orderItem=orderItemRepository.save(orderItem);
        return orderItem;
    }


    public OrderItem updateOrderItem(OrderItem orderItem) throws Exception{
        Long orderItemId = orderItem.getOrderItem();
        OrderItem existingOrderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new Exception("Item not exists with id: " + orderItemId));

        existingOrderItem.setQuantity(orderItem.getQuantity());
        existingOrderItem.setNotes(orderItem.getNotes());

        existingOrderItem = orderItemRepository.save(existingOrderItem);
        return existingOrderItem;

    }


    public void deleteOrderItem(Long id) throws Exception{
        OrderItem orderItem = orderItemRepository.findById(id).orElse(null);

        if(orderItem != null){
            orderItemRepository.deleteById(id);
        }
        else{
            throw new Exception("Item not exists with id:" + id);

        }
    }
























}
