package com.example.restaurantfoodorderingsystem.repositories;

import com.example.restaurantfoodorderingsystem.entities.CartItem;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem,Long> {


}
