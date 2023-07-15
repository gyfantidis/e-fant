package com.efant.efant.controllers;

import com.efant.efant.model.entities.OrderStatus;
import com.efant.efant.services.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderStatusController {
    private OrderStatusService orderStatusService;

    @Autowired
    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping("/orders/status")
    public List<OrderStatus> getOrderStatus() {
        return orderStatusService.getAllOrderStatus();
    }

    @GetMapping("/orders/status/{id}")
    public OrderStatus getOrderStatusById(@PathVariable Long id) throws Exception {
        return orderStatusService.getOrderStatusById(id);
    }


    @PostMapping("/orders/status")
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderStatus createOrderStatus(@RequestBody OrderStatus orderStatus) {
        orderStatus = orderStatusService.createOrderStatus(orderStatus);
        return orderStatus;
    }

    @PutMapping("/orders/status/{id}")
    public OrderStatus updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatus orderStatus) throws Exception {
        if (!id.equals(orderStatus.getOrderStatusId())) {
            throw new Exception("ID in path and ID in body are not the same");
        }

        orderStatus = orderStatusService.updateOrderStatus(orderStatus);
        return orderStatus;
    }


    @DeleteMapping("/orders/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderStatus(@PathVariable Long id) throws Exception {
        orderStatusService.deleteOrderStatus(id);
    }


}
