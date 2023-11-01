package com.project.customerservice.exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long id){
        super("The customer was not found");
    }
}
