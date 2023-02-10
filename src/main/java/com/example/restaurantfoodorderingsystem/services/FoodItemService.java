package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service

public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    @Autowired
    public FoodItemService(FoodItemRepository foodItemRepository){
        this.foodItemRepository = foodItemRepository;
    }
    public void createFoodItem(FoodItem foodItem){
        this.foodItemRepository.save(foodItem);
    }


    public void deleteItemById(Long foodItemId){
        this.foodItemRepository.deleteById(foodItemId);
    }

    public ArrayList<FoodItem> getAllFoodItems(){
        return this.foodItemRepository.findAll();
    }

    public FoodItem getAllFoodItemsById(Long foodItemId){
        return this.foodItemRepository.findFoodItemById(foodItemId);
    }


    public FoodItem updateFoodItemById(Long id, FoodItem updatedFoodItem) {
        FoodItem foodItem = foodItemRepository.findById(id).orElse(null); // this will return object, which may not contain a food item, so we check
        if (foodItem == null) {
            return null;
        }
        foodItem.setFoodName(updatedFoodItem.getFoodName());
        foodItem.setCategory(updatedFoodItem.getCategory());
        foodItem.setType(updatedFoodItem.getType());
        foodItem.setDescription(updatedFoodItem.getDescription());
        foodItem.setPrice(updatedFoodItem.getPrice());
        foodItem.setInStock(updatedFoodItem.isInStock());
        foodItem.setFoodPhotoUrl(updatedFoodItem.getFoodPhotoUrl());
        return foodItemRepository.save(foodItem);
    }


    public ArrayList<FoodItem> getAllFoodItemsByType(String foodItemType){
        return this.foodItemRepository.findFoodItemByType(foodItemType);
    }

}
