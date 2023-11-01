package com.project.customerservice.service;


import com.project.customerservice.dto.CustomerDTO;
import com.project.customerservice.dto.CustomerMapper;
import com.project.customerservice.entities.Customer;
import com.project.customerservice.exceptions.CustomerNotFoundException;
import com.project.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        if(repository.findById(id).isEmpty()){
            throw new CustomerNotFoundException(id);
        }else{
            repository.deleteById(id);
        }
        return "The customer was deleted successfully";
    }

    public ResponseEntity<Customer> update(Long id, CustomerDTO newCustomer){
            repository.findById(id).map(customer -> {
            customer.setName(newCustomer.getName());
            customer.setDocument(newCustomer.getDocument());
            customer.setTelephone(newCustomer.getTelephone());
            return repository.save(customer);

        }).orElseThrow(()->new CustomerNotFoundException(id));
            return null;
    }

    public Customer updateCustomerByFields(Long id, Map<String,Object> fields) {
        Optional<Customer> existingCustomer = repository.findById(id);
        if(existingCustomer.isPresent()){
            fields.forEach((key,value)->{
                Field field = org.springframework.util.ReflectionUtils.findField(Customer.class,key);
                field.setAccessible(true);
                org.springframework.data.util.ReflectionUtils.setField(
                        field,existingCustomer.get(),value);
            });
            return repository.save(existingCustomer.get());
        }
        return  null;
    }
}
