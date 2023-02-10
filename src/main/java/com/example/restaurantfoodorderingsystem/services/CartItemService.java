package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.CartItem;
import com.example.restaurantfoodorderingsystem.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository orderItemRepository;
    public CartItemService(CartItemRepository orderItemRepository){
        this.orderItemRepository = orderItemRepository;
    }
    public void addOrderedFoodItems(CartItem orderItem){
        orderItemRepository.save(orderItem);
    }
}
