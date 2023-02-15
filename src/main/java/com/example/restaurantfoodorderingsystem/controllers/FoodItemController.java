package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.services.FoodItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;

    }


//_______________________admin food_____________________________________

    @GetMapping("/adminAddMeal")
    public String showAddMealPage(){
        return "admin/adminAddMeal";
    }


    @PostMapping("/adminAddMeal")
    public String addNewFoodItem(FoodItem foodItem){
        this.foodItemService.createFoodItem(foodItem);

        return "redirect:/adminAddMeal";
    }



    @GetMapping("/adminViewAllMeal") //
    public String showAllMeal(Model model) {
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItems());
        return "admin/adminViewAllMeal";
    }


    @GetMapping("/adminViewAllMeal/delete/{foodItemId}")
    public String deleteFoodItem(@PathVariable(name = "foodItemId") Long foodItemId) {
        this.foodItemService.deleteItemById(foodItemId);
        return "redirect:/adminViewAllMeal?message=product_deleted";
    }

    @GetMapping("/edit{foodItemId}")
    public String showUpdateFoodItemForm(@PathVariable(name = "foodItemId") Long foodItemId, Model model) {
        model.addAttribute("foodItem", this.foodItemService.getAllFoodItemsById(foodItemId));
        return "admin/adminUpdateMeal";
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
    model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
    return "admin/adminViewAllMeal";
}


    @GetMapping("/fishMainDishes")
    public String showAllFishMainDishes(Model model) {
        String foodItemType = "Fish main dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/vegetableDishes")
    public String showAllVegetableDishes(Model model) {
        String foodItemType = "Vegetable dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }


    @GetMapping("/sideDishes")
    public String showAllSideDishes(Model model) {
        String foodItemType = "Side dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }


    @GetMapping("/soups")
    public String showAllSoups(Model model) {
        String foodItemType = "Soups";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/salads")
    public String showAllSalads(Model model) {
        String foodItemType = "Salads";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/pancakes")
    public String showAllPancakes(Model model) {
        String foodItemType = "Pancakes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }


    @GetMapping("/burgers")
    public String showAllBurgers(Model model) {
        String foodItemType = "Burgers";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/snacks")
    public String showAllSnacks(Model model) {
        String foodItemType = "Snacks";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/sauces")
    public String showAllSauces(Model model) {
        String foodItemType = "Sauces";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/bread")
    public String showAllBread(Model model) {
        String foodItemType = "Bread";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/desserts")
    public String showAllDesserts(Model model) {
        String foodItemType = "Desserts";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/bakery")
    public String showAllBakery(Model model) {
        String foodItemType = "Bakery";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/drinks")
    public String showAllDrinks(Model model) {
        String foodItemType = "Drinks";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }


    @GetMapping("/cutlery")
    public String showAllCutlery(Model model) {
        String foodItemType = "Cutlery";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "admin/adminViewAllMeal";
    }

//_____________________Menu customer_______________________________________________++++++

    // _____________________Kristine method___________________________________
    //this method opens page after login and shows all meals
    @GetMapping("menu/{customerId}")
    public String displayPageAfterLoginShowAllMeals(@PathVariable  Long customerId, Model model){
        model.addAttribute("customerId",customerId);
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItems());
        return "menu";
    }
    @GetMapping("menu/{customerId}/contactUs")
    public String displayContactUs(@PathVariable  Long customerId, Model model){
        model.addAttribute("customerId",customerId);
        return "customer/contactUs";
    }

    //________________Menu_Page_Liga_________________________________________

    @GetMapping("menu/{customerId}/menuMeatMainDishes")
    public String showMenuAllMeatMainDishes(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Meat main dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu"; // YOU NEED TO CHANGE ONLT THESE PATHS, PROBABLY STH LIKE  return "yourMapName/menu";
    }

    @GetMapping("menu/{customerId}/menuFishMainDishes")
    public String showMenuAllFishMainDishes(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Fish main dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuVegetableDishes")
    public String showMenuAllVegetableDishes(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Vegetable dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }


    @GetMapping("menu/{customerId}/menuSideDishes")
    public String showMenuAllSideDishes(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Side dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }


    @GetMapping("menu/{customerId}/menuSoups")
    public String showMenuAllSoups(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Soups";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuSalads")
    public String showMenuAllSalads(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Salads";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuPancakes")
    public String showMenuAllPancakes(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Pancakes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }


    @GetMapping("menu/{customerId}/menuBurgers")
    public String showMenuAllBurgers(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Burgers";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuSnacks")
    public String showMenuAllSnacks(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Snacks";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuSauces")
    public String showMenuAllSauces(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Sauces";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuBread")
    public String showMenuAllBread(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Bread";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuDesserts")
    public String showMenuAllDesserts(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Desserts";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuBakery")
    public String showMenuAllBakery(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Bakery";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuDrinks")
    public String showMenuAllDrinks(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Drinks";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }


    @GetMapping("menu/{customerId}/menuCutlery")
    public String showMenuAllCutlery(@PathVariable  Long customerId,Model model) {
        model.addAttribute("customerId",customerId);
        String foodItemType = "Cutlery";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }



}



