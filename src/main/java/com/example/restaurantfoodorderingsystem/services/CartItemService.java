package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.CartItem;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.repositories.CartItemRepository;
import jakarta.persistence.EntityManager;

public class CartItemService {
   private final CartItemRepository cartItemRepository;


    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }
private EntityManager entityManager;
    public void addOneCartItem(Customer customerId, FoodItem foodItemId, Integer quantity){
        Customer customer = entityManager.find(Customer.class,customerId);
        FoodItem foodItem = entityManager.find(FoodItem.class,foodItemId);

        CartItem newItem = new CartItem();
        newItem.setCustomer(customer);
        newItem.setFoodItem(foodItem);
        newItem.setQuantity(quantity);
        CartItem savedCartItem = cartItemRepository.save(newItem);
    }
}
