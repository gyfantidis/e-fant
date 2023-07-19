package com.efant.efant.controllers;

import com.efant.efant.model.entities.Order;
import com.efant.efant.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id) throws Exception{
        return orderService.getOrderById(id);
    }


    @PostMapping("/orders")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Order createOrder(@RequestBody Order order) throws Exception{
        order = orderService.createOrder(order);
        return order;
    }

    @PutMapping("/order/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) throws Exception{
        if (!id.equals(order.getOrderId())){
            throw new Exception("ID in path and ID in body are not the same");
        }

        order = orderService.updateOrder(order);
        return order;
    }

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) throws Exception {
        orderService.deleteOrder(id);
    }























}
