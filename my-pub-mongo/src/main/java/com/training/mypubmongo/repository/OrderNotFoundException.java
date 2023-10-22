package com.training.mypubmongo.repository;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id) {
        super("The customer's id: " + id + " doesn't exist");
    }
}
