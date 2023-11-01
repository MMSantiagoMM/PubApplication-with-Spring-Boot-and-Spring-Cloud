package com.training.mypubmongo.service;

import com.training.mypubmongo.dto.OrderDTO;
import com.training.mypubmongo.entity.Order;
import com.training.mypubmongo.exceptions.OrderNotFoundException;
import com.training.mypubmongo.feignclients.CustomerFeignClient;
import com.training.mypubmongo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
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

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public Order getById(Long id){
        return orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException(id));
    }


    public String saveOrder(OrderDTO orderDTO){
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
        return "The order was saved successfully";
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


    public ResponseEntity<Order> updateOrder (OrderDTO newOrder, Long id){
        orderRepository.findById(id)
                .map(order -> {
                    order.setId(newOrder.getId());
                    order.setCustomerFeign(customerFeignClient.getOneCustomer(newOrder.getIdCustomer()));
                    order.setDrinks(newOrder.getDrinks());
                    order.setTable(newOrder.getTable());
                    order.setDiscount(newOrder.getDiscount());
                    order.setTotal(calculateTotalWithDiscount(
                            calculateTotalBill(newOrder.getDrinks()),
                            newOrder.getDiscount()
                    ));
                    order.setTotalWithTip(calculateTotalWithTip(order.getTotal()));
                    order.setTotalWithTaxes(calculateTotalWithTaxes(order.getTotalWithTip()));
                    return orderRepository.save(order);
                }).orElseThrow(()-> new OrderNotFoundException(id));
        return null;

    }

    public String deleteOrder(Long id){
        if(orderRepository.findById(id).isEmpty()){
            return "The order doesn't exist";
        }else{
            orderRepository.deleteById(id);
            return "The order was deleted successfully";
        }
    }

    public Order updateOrderByField(Long id, Map<String,Object>fields){
        Optional<Order> existingCustomer = orderRepository.findById(id);
        if(existingCustomer.isPresent()){
            fields.forEach((key,value)->{
                Field field = ReflectionUtils.findField(Order.class,key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,existingCustomer.get(),value);
            });
            return orderRepository.save(existingCustomer.get());
        }
        return null;
    }
}


