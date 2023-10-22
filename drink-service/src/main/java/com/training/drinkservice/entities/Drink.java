package com.training.drinkservice.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Drinks")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private Double price;
    private String size;
    private String region;
    private Double percentAlcohol;
}
