package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.services.AdminService;
import com.example.restaurantfoodorderingsystem.services.FoodItemService;
import com.example.restaurantfoodorderingsystem.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FoodItemController {

    private final FoodItemService foodItemService;
    private final AdminService adminService;

    private final OrderService orderService;


//    public FoodItemController(FoodItemService foodItemService, AdminService adminService) {
//        this.foodItemService = foodItemService;
//        this.adminService = adminService;
//    }

    public FoodItemController(FoodItemService foodItemService, AdminService adminService, OrderService orderService) {
        this.foodItemService = foodItemService;
        this.adminService = adminService;
        this.orderService= orderService;
    }


    @GetMapping("/adminProfileView/{adminId}/adminAllOrder")
    public String showAllOrders(@CookieValue(value = "adminCookie") String adminIdCookie, @PathVariable("adminId") Long adminId, Model model){
        model.addAttribute("adminId", adminIdCookie);
        model.addAttribute("orderList", this.orderService.getAllOrders());
        return "admin/adminAllOrder";
    }


    @GetMapping("/adminProfileView/{adminId}/adminAddMeal")
    public String displayAdminAddMeal(@CookieValue(value = "adminCookie") String adminIdCookie, @PathVariable("adminId") Long adminId, Model model){
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminAddMeal";
    }
    @PostMapping("/adminProfileView/{adminId}/adminAddMeal")
    public String addNewFoodItem(@PathVariable("adminId") Long adminId, FoodItem foodItem, @CookieValue(value = "adminCookie") String adminIdCookie, Model model){
        model.addAttribute("adminId", adminService.findAdminById(adminId));
        this.foodItemService.createFoodItem(foodItem);
        return "redirect:/adminProfileView/{adminId}/adminAddMeal";
    }
    @GetMapping("/adminProfileView/{adminId}/adminViewAllMeal")
    public String showAllMeal(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        model.addAttribute("adminId", adminIdCookie);
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItems());
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/adminViewAllMeal/delete/{foodItemId}")
    public String deleteFoodItem(@PathVariable(name = "foodItemId") Long foodItemId, @PathVariable(name = "adminId") Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        model.addAttribute("adminId", adminIdCookie);
        model.addAttribute("foodItemId", foodItemId);
        this.foodItemService.deleteItemById(foodItemId);
        return "redirect:/adminProfileView/{adminId}/adminViewAllMeal?message=product_deleted";
    }

    @GetMapping("/adminProfileView/{adminId}/edit/{foodItemId}")
    public String showUpdateFoodItemForm(@PathVariable(name = "foodItemId") Long foodItemId, Model model, @CookieValue(value = "adminCookie") String adminIdCookie, @PathVariable Long adminId) {
        model.addAttribute("adminId", adminIdCookie);
        model.addAttribute("foodItem", this.foodItemService.getAllFoodItemsById(foodItemId));
        return "admin/adminUpdateMeal";
    }
    @PostMapping("/adminProfileView/{adminId}/updateMeal/{foodItemId}")
    public String updateFoodItem(@PathVariable(name="foodItemId") Long foodItemId, FoodItem updatedFoodItem, @CookieValue(value = "adminCookie") String adminIdCookie, @PathVariable Long adminId, Model model) {
//        this.adminService.findAdminById(adminId);
        model.addAttribute("adminId", adminIdCookie);
        model.addAttribute("foodItemId", foodItemId);
        this.foodItemService.updateFoodItemById(foodItemId, updatedFoodItem);
        this.foodItemService.createFoodItem(updatedFoodItem);
        return "redirect:/adminProfileView/{adminId}/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/meatMainDishes")
    public String showAllMeatMainDishes(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie,Model model) {
        String foodItemType = "Meat main dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }


    @GetMapping("/adminProfileView/{adminId}/fishMainDishes")
    public String showAllFishMainDishes(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie,Model model) {
        String foodItemType = "Fish main dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/vegetableDishes")
    public String showAllVegetableDishes(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie,Model model) {
        String foodItemType = "Vegetable dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }


    @GetMapping("/adminProfileView/{adminId}/sideDishes")
    public String showAllSideDishes(@PathVariable Long adminId,Model model, @CookieValue(value = "adminCookie") String adminIdCookie) {
        String foodItemType = "Side dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }


    @GetMapping("/adminProfileView/{adminId}/soups")
    public String showAllSoups(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie,Model model) {
        String foodItemType = "Soups";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/salads")
    public String showAllSalads(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Salads";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/pancakes")
    public String showAllPancakes(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie,Model model) {
        String foodItemType = "Pancakes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }


    @GetMapping("/adminProfileView/{adminId}/burgers")
    public String showAllBurgers(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie,Model model) {
        String foodItemType = "Burgers";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/snacks")
    public String showAllSnacks(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Snacks";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/sauces")
    public String showAllSauces(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Sauces";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/bread")
    public String showAllBread(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Bread";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/desserts")
    public String showAllDesserts(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Desserts";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/bakery")
    public String showAllBakery(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Bakery";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/drinks") //  by category
    public String showAllDrinks(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemCategory = "Drinks";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByCategory(foodItemCategory));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }


    @GetMapping("/adminProfileView/{adminId}/cutlery")
    public String showAllCutlery(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Cutlery";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/tea")
    public String showAllTea(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Tea";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/coffee")
    public String showAllCoffee(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Coffee";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }

    @GetMapping("/adminProfileView/{adminId}/water")
    public String showAllWater(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Water";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }
    @GetMapping("/adminProfileView/{adminId}/juice")
    public String showAllJuice(@PathVariable Long adminId, @CookieValue(value = "adminCookie") String adminIdCookie, Model model) {
        String foodItemType = "Juice";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        model.addAttribute("adminId", adminIdCookie);
        return "admin/adminViewAllMeal";
    }



//_______________________admin food_____________________________________

//    @GetMapping("/adminAddMeal")
//    public String showAddMealPage(){
//        return "admin/adminAddMeal";
//    }
//
//
//    @PostMapping("/adminAddMeal")
//    public String addNewFoodItem(FoodItem foodItem){
//        this.foodItemService.createFoodItem(foodItem);
//
//        return "redirect:/adminAddMeal";
//    }



//    @GetMapping("/adminViewAllMeal") //
//    public String showAllMeal(Model model) {
//        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItems());
//        return "admin/adminViewAllMeal";
//    }
//
//
//    @GetMapping("/adminViewAllMeal/delete/{foodItemId}")
//    public String deleteFoodItem(@PathVariable(name = "foodItemId") Long foodItemId) {
//        this.foodItemService.deleteItemById(foodItemId);
//        return "redirect:/adminViewAllMeal?message=product_deleted";
//    }
//
//    @GetMapping("/edit{foodItemId}")
//    public String showUpdateFoodItemForm(@PathVariable(name = "foodItemId") Long foodItemId, Model model) {
//        model.addAttribute("foodItem", this.foodItemService.getAllFoodItemsById(foodItemId));
//        return "admin/adminUpdateMeal";
//    }
//
//
//    @PostMapping("/updateMeal/{foodItemId}")
//    public String updateFoodItem(@PathVariable(name="foodItemId") Long foodItemId, FoodItem updatedFoodItem) {
//        this.foodItemService.updateFoodItemById(foodItemId, updatedFoodItem);
//        this.foodItemService.createFoodItem(updatedFoodItem);
//        return "redirect:/adminViewAllMeal";
//    }


// Sort FoodItems by types
//    Marija: changed @GetMapping("/adminProfileView/{adminId}/meatMainDishes")


//_____________________Menu customer_______________________________________________++++++

    // _____________________Kristine method___________________________________
    //this method opens page after login and shows all meals
    @GetMapping("menu/{customerId}")
    public String displayPageAfterLoginShowAllMeals(@PathVariable  Long customerId, Model model,
                                                    @CookieValue(value = "customerCookie")String customerIdFromCookie){
        try {
            // System.out.println("cookie: " +customerIdFromCookie);
            model.addAttribute("customerId",customerIdFromCookie);
            // model.addAttribute("customerCookie",customerService.findAllCustomersById(Long.valueOf(customerIdFromCookie)));
            model.addAttribute("foodItemList", this.foodItemService.getAllFoodItems());
            return "menu";
        }catch (Exception e){
            return "redirect:/login?message=customer_not_found";

        }


    }
    @GetMapping("menu/{customerId}/contactUs")
    public String displayContactUs(@PathVariable  Long customerId, Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) throws Exception {
        // model.addAttribute("customerId",customerId);
        model.addAttribute("customerId",customerIdFromCookie);
        return "customer/contactUs";
    }

//    @GetMapping("/contactUs")
//    public String displayContactUsOnIndexPage(){
//
//        return "customer/contactUs";
//    }

    //________________Menu_Page_Liga_________________________________________

    @GetMapping("menu/{customerId}/menuMeatMainDishes")
    public String showMenuAllMeatMainDishes(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Meat main dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu"; // YOU NEED TO CHANGE ONLT THESE PATHS, PROBABLY STH LIKE  return "yourMapName/menu";
    }

    @GetMapping("menu/{customerId}/menuFishMainDishes")
    public String showMenuAllFishMainDishes(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Fish main dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuVegetableDishes")
    public String showMenuAllVegetableDishes(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Vegetable dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }


    @GetMapping("menu/{customerId}/menuSideDishes")
    public String showMenuAllSideDishes(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Side dishes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }


    @GetMapping("menu/{customerId}/menuSoups")
    public String showMenuAllSoups(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Soups";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuSalads")
    public String showMenuAllSalads(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Salads";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuPancakes")
    public String showMenuAllPancakes(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Pancakes";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }


    @GetMapping("menu/{customerId}/menuBurgers")
    public String showMenuAllBurgers(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Burgers";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuSnacks")
    public String showMenuAllSnacks(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Snacks";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuSauces")
    public String showMenuAllSauces(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Sauces";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuBread")
    public String showMenuAllBread(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Bread";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }

//    @GetMapping("menu/{customerId}/menuDesserts")
//    public String showMenuAllDesserts(@PathVariable  Long customerId,Model model) {
//        model.addAttribute("customerId",customerId);
//        String foodItemType = "Desserts";
//        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
//        return "menu";
//    }

    @GetMapping("menu/{customerId}/menuBakery")
    public String showMenuAllBakery(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Bakery";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }


    @GetMapping("menu/{customerId}/menuCutlery")
    public String showMenuAllCutlery(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemType = "Cutlery";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByType(foodItemType));
        return "menu";
    }


    // by category

    @GetMapping("menu/{customerId}/menuDrinks")
    public String showMenuAllDrinks(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemCategory = "Drinks";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByCategory(foodItemCategory));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuBreakfast")
    public String showMenuBreakfast(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemCategory = "Breakfast";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByCategory(foodItemCategory));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuBusinessLunch")
    public String showMenuBusinessLunch(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemCategory = "Business Lunch";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByCategory(foodItemCategory));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuDesserts")
    public String showMenuDesserts(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemCategory = "Desserts";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByCategory(foodItemCategory));
        return "menu";
    }

    @GetMapping("menu/{customerId}/menuSpecialOffer")
    public String showMenuSpecialOffer(@PathVariable  Long customerId,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) {
        model.addAttribute("customerId",customerIdFromCookie);
        String foodItemCategory = "Special Offer";
        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItemsByCategory(foodItemCategory));
        return "menu";
    }


}



