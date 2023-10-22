package com.training.mypubmongo.service;

import com.training.mypubmongo.dto.OrderDTO;
import com.training.mypubmongo.entity.Order;
import com.training.mypubmongo.feignclients.CustomerFeignClient;
import com.training.mypubmongo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;


    @Autowired
    private final CustomerFeignClient customerFeignClient;

    public OrderService(OrderRepository orderRepository, CustomerFeignClient customerFeignClient) {
        this.orderRepository = orderRepository;
        this.customerFeignClient = customerFeignClient;
    }


    public OrderDTO saveOrder(OrderDTO orderDTO){
        Order order = new Order();
        order.setId(orderDTO.getId());

        order.setCustomerFeign(
                customerFeignClient.getOneCustomer(orderDTO.getIdCustomer())
        );

        order.setDrinks(orderDTO.getDrinks());
        order.setTable(orderDTO.getTable());
        order.setDiscount(orderDTO.getDiscount());
        order.setTotal(calculateTotalWithDiscount(
                calculateTotalBill(orderDTO.getDrinks()),
                orderDTO.getDiscount()
        ));
        order.setTotalWithTip(calculateTotalWithTip(order.getTotal()));
        order.setTotalWithTaxes(calculateTotalWithTaxes(order.getTotalWithTip()));
        orderRepository.save(order);
        return orderDTO;
    }



    public Double calculateTotalBill(Map<String,Double> drinks){
        Collection<Double> valueDrinks = drinks.values();
        Optional<Double> totalOptional = valueDrinks.stream().reduce(Double::sum);
        return totalOptional.get();
    }

    public Double calculateTotalWithDiscount(Double total,Double discount){
        return total - discount;
    }

    public Double calculateTotalWithTip(Double total){
        return total + (total * 0.10);
    }

    public Double calculateTotalWithTaxes(Double total){
        return total + (total * 0.19);
    }
}
