package com.project.customerservice.dto;

import com.project.customerservice.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    @Mapping(target = "id",ignore = true)
    Customer ToCustomer (CustomerDTO customerDTO);


}
