//package com.example.restaurantfoodorderingsystem.services;
//
//import com.example.restaurantfoodorderingsystem.entities.Order;
//import com.example.restaurantfoodorderingsystem.repositories.CartItemRepository;
//import com.example.restaurantfoodorderingsystem.repositories.FoodItemRepository;
//import com.example.restaurantfoodorderingsystem.repositories.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class OrderService {
//    private final OrderRepository orderRepository;
////    private final CustomerRepository customerRepository;
//    private final CartItemRepository cartItemRepository;
//
//    @Autowired
//    public OrderService(OrderRepository orderRepository, CartItemRepository cartItemRepository){
//        this.orderRepository = orderRepository;
//        this.cartItemRepository = cartItemRepository;
//    }
//
//    public void createOrder(Order order){
//        this.orderRepository.save(order);
//    }
//    public ArrayList<Order> getAll(){
//        return orderRepository.findAll();
//    }
//    public Order findById(Long id){
//        return this.orderRepository.findOrderById(id);
//    }
////
////    public void deleteById(Long id){
////        this.orderRepository.deleteById(id);
////    }
//}
