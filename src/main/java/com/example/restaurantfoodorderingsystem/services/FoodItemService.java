package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class FoodItemService {

private final FoodItemRepository foodItemRepository;

@Autowired
    public FoodItemService(FoodItemRepository foodItemRepository){
    this.foodItemRepository = foodItemRepository;
}
    public void createFoodItem(FoodItem foodItem)throws Exception{
        this.foodItemRepository.save(foodItem);
    }


}





