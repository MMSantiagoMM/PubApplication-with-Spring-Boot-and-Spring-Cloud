package com.training.drinkservice.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Drinks")
public class Drink {

    @Id
    @GeneratedValue
    private Long id;
    private String drink;
    private Double price;
    private String size;
    private String region;
    private Double percentAlcohol;
}
