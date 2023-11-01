package com.training.mypubmongo.controller;


import com.mongodb.internal.bulk.UpdateRequest;
import com.training.mypubmongo.dto.OrderDTO;
import com.training.mypubmongo.entity.Order;
import com.training.mypubmongo.exceptions.OrderNotFoundException;
import com.training.mypubmongo.repository.OrderRepository;
import com.training.mypubmongo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pub")
public class OrderController {

    @Autowired
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all_orders")
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<Order> getall(){
        return orderService.getAll();
    }

    @GetMapping("/one_order/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Order getOneCustomer(@PathVariable Long id){
        return orderService.getById(id);
    }

    @PostMapping("/insert_order")
    @ResponseStatus(HttpStatus.CREATED)
    String insert(@RequestBody OrderDTO orderDTO){

        return orderService.saveOrder(orderDTO);
    }

    @PutMapping("/update_order/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Order> updateCustomer(@PathVariable Long id, @RequestBody OrderDTO newOrder){
        return orderService.updateOrder(newOrder,id);
    }

    @DeleteMapping("/remove_order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCustomer(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }
}












