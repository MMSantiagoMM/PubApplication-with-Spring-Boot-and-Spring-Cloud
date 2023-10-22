package com.training.mypubmongo.repository;

import com.training.mypubmongo.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order,Long> {
}
