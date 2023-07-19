package com.efant.efant.services;

import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.entities.OrderItem;
import com.efant.efant.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new EfantException("ORDER_ITEM_NOT_FOUND", "Order Item not exists with id: " + id, HttpStatus.NOT_FOUND));

    }

    public OrderItem createOrderItem(OrderItem orderItem) throws Exception{
        if (orderItem.getOrderItem() != null) {
            throw new EfantException("NEW_ORDER_ITEM_ID_IS_NOT_NULL", "Order Items id must be null", HttpStatus.BAD_REQUEST);
        }
        orderItem=orderItemRepository.save(orderItem);
        return orderItem;
    }


    public OrderItem updateOrderItem(OrderItem orderItem) throws Exception{
        Long orderItemId = orderItem.getOrderItem();
        OrderItem existingOrderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new EfantException("ORDERS_ITEM_NOT_FOUND", "Orders Item not exists with id: " + orderItemId, HttpStatus.NOT_FOUND));

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
            throw new EfantException("ORDERS_ITEM_NOT_FOUND", "Orders Item not exists with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
























}
