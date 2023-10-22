package com.training.mypubmongo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDTO {

    private Long id;
    private Integer table;
    private Map<String,Double> drinks;
    private Double discount;
    private Long idCustomer;

}
