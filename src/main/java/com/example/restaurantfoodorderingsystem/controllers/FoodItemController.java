package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.repositories.FoodItemRepository;
import com.example.restaurantfoodorderingsystem.services.FoodItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;

    }

    // Kristine method
    @GetMapping("customerPageAfterLogin/{customerId}")
    public String displayPageAfterLogin(
            @PathVariable Integer customerId, Model model
    ){
        model.addAttribute("customerId",customerId);
        return "customerPageAfterLogin";
    }



    @GetMapping("/adminAddMeal")
    public String showAddMealPage(){
        return "adminAddMeal";
    }


    @PostMapping("/adminAddMeal")
    public String addNewFoodItem(FoodItem foodItem){
        this.foodItemService.createFoodItem(foodItem);

        return "redirect:adminAddMeal";
    }



    @GetMapping("/adminViewAllMeal") //
    public String showAllMeal(Model model) {
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItems());
        return "adminViewAllMeal";
    }


    @GetMapping("/adminViewAllMeal/delete/{foodItemId}")
    public String deleteFoodItem(@PathVariable(name = "foodItemId") Long foodItemId) {
        this.foodItemService.deleteItemById(foodItemId);
        return "redirect:/adminViewAllMeal?message=product_deleted";
    }

    @GetMapping("/edit{foodItemId}")
    public String showUpdateFoodItemForm(@PathVariable(name = "foodItemId") Long foodItemId, Model model) {
        model.addAttribute("foodItem", this.foodItemService.getAllFoodItemsById(foodItemId));
        return "adminUpdateMeal";
    }


    @PostMapping("/updateMeal/{foodItemId}")
    public String updateFoodItem(@PathVariable(name="foodItemId") Long foodItemId, FoodItem updatedFoodItem) {
        this.foodItemService.updateFoodItemById(foodItemId, updatedFoodItem);
        this.foodItemService.createFoodItem(updatedFoodItem);
        return "redirect:/adminViewAllMeal";
    }


// Sort FoodItems by types
@GetMapping("/meatMainDishes")
public String showAllMeatMainDishes(Model model) {
    String foodItemType = "Meat main dishes";
    model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByTypeMainDish(foodItemType));
    return "adminViewAllMeal";
}


    @GetMapping("/fishMainDishes")
    public String showAllFishMainDishes(Model model) {
        String foodItemType = "Fish main dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByTypeFishDish(foodItemType));
        return "adminViewAllMeal";
    }

    @GetMapping("/vegetableDishes")
    public String showAllVegetableDishes(Model model) {
        String foodItemType = "Vegetable dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByTypeVegetableDish(foodItemType));
        return "adminViewAllMeal";
    }


    @GetMapping("/sideDishes")
    public String showAllSideDishes(Model model) {
        String foodItemType = "Side dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByTypeSideDish(foodItemType));
        return "adminViewAllMeal";
    }


    @GetMapping("/soups")
    public String showAllSoups(Model model) {
        String foodItemType = "Soups";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByTypeSoups(foodItemType));
        return "adminViewAllMeal";
    }

    @GetMapping("/salads")
    public String showAllSalads(Model model) {
        String foodItemType = "Salads";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByTypeSalads(foodItemType));
        return "adminViewAllMeal";
    }



}



