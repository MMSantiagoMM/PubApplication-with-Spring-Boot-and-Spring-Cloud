package com.project.customerservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerDTO {
    private Long id;
    private String name;
    private String document;
    private String telephone;
}
