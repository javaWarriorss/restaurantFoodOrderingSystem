package com.example.restaurantfoodorderingsystem.repositories;

import com.example.restaurantfoodorderingsystem.entities.CartItem;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    @Override
    ArrayList<Order> findAll();

//    Order getOrdersByCustomerId(Long customerId); // returns only one order?
    List<Order> getOrdersByCustomerIdOrderByOrderTimeDesc(Long customerId);

}
