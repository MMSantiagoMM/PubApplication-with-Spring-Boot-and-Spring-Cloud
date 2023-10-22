package com.training.mypubmongo.feignclients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerFeignClient {

    @GetMapping("/customer/all")
    List<CustomerFeign> getCustomers();

    @GetMapping("customer/one/{id}")
    CustomerFeign getOneCustomer(@PathVariable Long id);


}
