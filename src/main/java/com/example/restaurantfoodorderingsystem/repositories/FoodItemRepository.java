package com.example.restaurantfoodorderingsystem.repositories;

import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FoodItemRepository extends CrudRepository<FoodItem, Long> {



}

