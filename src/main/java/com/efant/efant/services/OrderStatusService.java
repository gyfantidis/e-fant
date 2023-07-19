package com.efant.efant.services;

import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.entities.OrderStatus;
import com.efant.efant.repositories.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {

    private OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }


    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
    }

    public OrderStatus getOrderStatusById(Long id) throws Exception {
        return orderStatusRepository.findById(id)
                .orElseThrow(() -> new EfantException("ORDER_STATUS_NOT_FOUND", "Orders Status not exists with id: " + id, HttpStatus.NOT_FOUND));

    }


    public OrderStatus createOrderStatus(OrderStatus orderStatus) throws Exception {
        if (orderStatus.getOrderStatusId() != null) {
            throw new EfantException("NEW_ORDERS_STATUS_ID_IS_NOT_NULL", "Orders Status id must be null", HttpStatus.BAD_REQUEST);
        }
        orderStatus = orderStatusRepository.save(orderStatus);
        return orderStatus;
    }


    public OrderStatus updateOrderStatus(OrderStatus orderStatus) throws Exception {
        Long orderStatusId = orderStatus.getOrderStatusId();
        OrderStatus existingOrderStatus = orderStatusRepository.findById(orderStatusId)
                .orElseThrow(() -> new EfantException("ORDERS_STATUS_NOT_FOUND", "Orders Status not exists with id: " + orderStatusId, HttpStatus.NOT_FOUND));

        existingOrderStatus.setReceived(orderStatus.getReceived());
        existingOrderStatus.setProcessing(orderStatus.getProcessing());
        existingOrderStatus.setOnTheRoad(orderStatus.getOnTheRoad());
        existingOrderStatus.setDelivered(orderStatus.getDelivered());
        existingOrderStatus.setStatus(orderStatus.getStatus());

        existingOrderStatus = orderStatusRepository.save(existingOrderStatus);
        return existingOrderStatus;
    }

    public void deleteOrderStatus(Long id) throws Exception {
        OrderStatus orderStatus = orderStatusRepository.findById(id).orElse(null);

        if (orderStatus != null) {
            orderStatusRepository.deleteById(id);
        } else {
            throw new EfantException("ORDERS_STATUS_NOT_FOUND", "Orders Status not exists with id: " + id, HttpStatus.NOT_FOUND);
        }
    }


}
