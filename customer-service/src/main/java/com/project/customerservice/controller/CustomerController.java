package com.project.customerservice.controller;


import com.project.customerservice.dto.CustomerDTO;
import com.project.customerservice.entities.Customer;
import com.project.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    List<Customer> getCustomers(){
        return service.getAll();
    }

    @GetMapping("/one/{id}")
    @ResponseStatus(HttpStatus.OK)
    Customer getOneCustomer(@PathVariable Long id){
        return service.getOne(id);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    CustomerDTO create(@RequestBody CustomerDTO customerDTO){
        return service.create(customerDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    String delete(@PathVariable Long id){
        return service.remove(id);
    }


}
