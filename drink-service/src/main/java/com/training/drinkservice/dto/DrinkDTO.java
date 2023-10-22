package com.training.drinkservice.dto;

import lombok.Data;

@Data
public class DrinkDTO {

    private String type;
    private String name;
    private Double price;
    private String size;
    private String region;
    private Double percentAlcohol;
}
