package com.training.mypubmongo.feignclients;


import lombok.Data;

@Data
public class CustomerFeign {
    private Long id;
    private String document;
    private String name;
    private String telephone;
}
