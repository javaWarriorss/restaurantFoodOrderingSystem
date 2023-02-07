package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.Order;
import com.example.restaurantfoodorderingsystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
//    private final CustomerRepository customerRepository;
//    private final FoodItemRepository foodItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void createOrder(Order order){
        this.orderRepository.save(order);
    }

    public ArrayList<Order> getAll(){
        return orderRepository.findAll();
    }
    public Order findById(Long id){
        return this.orderRepository.findOrderById(id);
    }

    public void deleteById(Long id){
        this.orderRepository.deleteById(id);
    }
}
