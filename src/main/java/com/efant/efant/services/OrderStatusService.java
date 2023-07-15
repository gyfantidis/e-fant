package com.efant.efant.services;

import com.efant.efant.model.entities.OrderStatus;
import com.efant.efant.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {

    private OrderStatusRepository orderStatusRepository;
    @Autowired
    public OrderStatusService(OrderStatusRepository orderStatusRepository){
        this.orderStatusRepository = orderStatusRepository;
    }


    public List<OrderStatus> getAllOrderStatus(){
        return orderStatusRepository.findAll();
    }

    public OrderStatus getOrderStatusById(Long id) throws Exception{
        return orderStatusRepository.findById(id)
                .orElseThrow(() -> new Exception("Status not exists with id: " + id));

    }


    public OrderStatus createOrderStatus(OrderStatus orderStatus){
        orderStatus = orderStatusRepository.save(orderStatus);
        return orderStatus;
    }


    public OrderStatus updateOrderStatus(OrderStatus orderStatus) throws Exception{
        Long orderStatusId = orderStatus.getOrderStatusId();
        OrderStatus existingOrderStatus = orderStatusRepository.findById(orderStatusId)
                .orElseThrow(() -> new Exception("Status not exists with id: " + orderStatusId));

        existingOrderStatus.setReceived(orderStatus.getReceived());
        existingOrderStatus.setProcessing(orderStatus.getProcessing());
        existingOrderStatus.setOnTheRoad(orderStatus.getOnTheRoad());
        existingOrderStatus.setDelivered(orderStatus.getDelivered());
        existingOrderStatus.setStatus(orderStatus.getStatus());

        existingOrderStatus = orderStatusRepository.save(existingOrderStatus);
        return existingOrderStatus;
    }

    public void deleteOrderStatus(Long id) throws Exception{
        OrderStatus orderStatus = orderStatusRepository.findById(id).orElse(null);

        if (orderStatus != null){
            orderStatusRepository.deleteById(id);
        }
        else{
            throw new Exception("Status not exists with id:" + id);
        }
    }


















}
