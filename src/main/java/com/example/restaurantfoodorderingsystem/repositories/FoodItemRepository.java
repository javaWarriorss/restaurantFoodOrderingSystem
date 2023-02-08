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



    FoodItem findFoodItemByType(String type);

//    ArrayList<FoodItem> findByAndSort(String category, Sort sort);


//User findByUserNameAndPasswordOrderById(String userName, String password);


    FoodItem findFoodItemByTypeOrderByTypeAsc(String type);

}