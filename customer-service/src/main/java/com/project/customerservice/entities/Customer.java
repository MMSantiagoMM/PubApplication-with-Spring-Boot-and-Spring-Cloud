package com.project.customerservice.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String document;
    private String telephone;
}
