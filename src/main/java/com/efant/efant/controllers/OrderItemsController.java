package com.efant.efant.controllers;

import com.efant.efant.model.entities.OrderItem;
import com.efant.efant.services.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderItemsController {

    private OrderItemsService orderItemsService;

    @Autowired
    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    @GetMapping("/orders/items")
    public List<OrderItem> getOrderItems() {
        return orderItemsService.getAllOrderItems();
    }

    @GetMapping("/orders/items/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) throws Exception {
        return orderItemsService.getOrderItemById(id);
    }

    @PostMapping("/orders/items")
    @ResponseStatus(value = HttpStatus.CREATED)
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        orderItem = orderItemsService.createOrderItem(orderItem);
        return orderItem;
    }

    @PutMapping("/orders/items/{id}")
    public OrderItem updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItem) throws Exception {
        if (!id.equals(orderItem.getOrderItem())) {
            throw new Exception("ID in path and ID in body are not the same");
        }

        orderItem = orderItemsService.updateOrderItem(orderItem);
        return orderItem;
    }

    @DeleteMapping("/orders/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderItem(@PathVariable Long id) throws Exception {
        orderItemsService.deleteOrderItem(id);
    }


}
