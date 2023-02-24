package com.example.restaurantfoodorderingsystem.repositories;

import com.example.restaurantfoodorderingsystem.entities.CartItem;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem,Long> {

 List<CartItem> findByCustomer(Customer customer);
 CartItem findByCustomerAndFoodItem(Customer customer, FoodItem foodItem);
 void deleteCartItemByCustomer_IdAndFoodItem_Id (Long customer,Long foodItem);
 void deleteByCustomerId(Long customerId);

}
