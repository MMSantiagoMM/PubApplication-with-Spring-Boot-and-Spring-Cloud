package com.training.mypubmongo.controller;


import com.training.mypubmongo.feignclients.CustomerFeign;
import com.training.mypubmongo.dto.OrderDTO;
import com.training.mypubmongo.entity.Order;
import com.training.mypubmongo.feignclients.CustomerFeignClient;
import com.training.mypubmongo.repository.OrderNotFoundException;
import com.training.mypubmongo.repository.OrderRepository;
import com.training.mypubmongo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pub")
public class OrderController {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final CustomerFeignClient customerFeignClient;

    public OrderController(OrderRepository orderRepository, OrderService orderService, CustomerFeignClient customerFeignClient) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.customerFeignClient = customerFeignClient;
    }

    @GetMapping("/allcustomers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    List<Order> getall(){
        return orderRepository.findAll();
    }

    @GetMapping("/onecustomer/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Order getOneCustomer(@PathVariable Long id){
        return orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException(id));
    }

    @PostMapping("/insertcustomer")
    @ResponseStatus(HttpStatus.CREATED)
    String insert(@RequestBody OrderDTO orderDTO){
        orderService.saveCustomer(orderDTO);
        return "A customer was created";
    }

    @PutMapping("/updateCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
    String updateCustomer(@PathVariable Long id, @RequestBody OrderDTO orderDTO){
        orderService.saveCustomer(orderDTO);
        return "A customer was updated";
    }

    @DeleteMapping("/removeCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean deleteCustomer(@PathVariable Long id){
        if(!orderRepository.findById(id).equals(Optional.empty())){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}












