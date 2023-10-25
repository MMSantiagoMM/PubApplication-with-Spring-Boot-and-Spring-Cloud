package com.training.mypubmongo.entity;


import com.training.mypubmongo.feignclients.CustomerFeign;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Map;

@Data
@Document(collection = "Order")
public class Order {

    @MongoId
    private Long id;
    private CustomerFeign customerFeign;
    private Integer table;
    private Map<String,Double> drinks;
    private Double discount;
    private Double total;
    private Double totalWithTip;
    private Double totalWithTaxes;

}
