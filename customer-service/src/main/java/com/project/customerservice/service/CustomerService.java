package com.project.customerservice.service;


import com.project.customerservice.dto.CustomerDTO;
import com.project.customerservice.dto.CustomerMapper;
import com.project.customerservice.entities.Customer;
import com.project.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    @Autowired
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public String create(CustomerDTO customerDTO){
        repository.save(CustomerMapper.INSTANCE.ToCustomer(customerDTO));
        return "Customer was created successfully";
    }

    public List<Customer> getAll(){
        return repository.findAll();
    }

    public Customer getOne(Long id){
        return repository.findById(id).orElse(null);
    }

    public String remove (Long id){
        repository.deleteById(id);
        return "Customer deleted successful";
    }




}
