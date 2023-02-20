package com.example.restaurantfoodorderingsystem.repositories;

import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface FoodItemRepository extends CrudRepository<FoodItem, Long> {

//    FoodItem findFoodItemById(Long id);

    FoodItem findFoodItemById(Long id);


    @Override
    ArrayList<FoodItem> findAll();  // override function, to change behaviour, for example to change data type



//User findByUserNameAndPasswordOrderById(String userName, String password);


    ArrayList<FoodItem> findFoodItemByType(String type);


    ArrayList<FoodItem> findFoodItemByCategory(String foodItemCategory);
}