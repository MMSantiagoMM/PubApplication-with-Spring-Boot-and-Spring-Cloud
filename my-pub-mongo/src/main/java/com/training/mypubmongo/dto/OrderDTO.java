package com.training.mypubmongo.dto;


import lombok.*;

import java.util.Map;


@Data
public class OrderDTO {

    private Long id;
    private Integer table;
    private Map<String,Double> drinks;
    private Double discount;
    private Long idCustomer;

}
