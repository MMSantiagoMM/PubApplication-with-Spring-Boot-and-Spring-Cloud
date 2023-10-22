package com.project.customerservice.controller;


import com.project.customerservice.dto.CustomerDTO;
import com.project.customerservice.entities.Customer;
import com.project.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/all")
    List<Customer> getCustomers(){
        return service.getAll();
    }

    @GetMapping("/one/{id}")
    Customer getOneCustomer(@PathVariable Long id){
        return service.getOne(id);
    }

    @PostMapping("/insert")
    CustomerDTO create(@RequestBody CustomerDTO customerDTO){
        return service.create(customerDTO);
    }

    @DeleteMapping("/delete/{id}")
    String delete(@PathVariable Long id){
        return service.remove(id);
    }


}
