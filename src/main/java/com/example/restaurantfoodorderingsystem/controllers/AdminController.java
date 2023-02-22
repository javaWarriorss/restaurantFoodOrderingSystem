package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.Admin;
import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.services.AdminService;
import com.example.restaurantfoodorderingsystem.services.FoodItemService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final AdminService adminService;

    private final FoodItemService foodItemService;
    @Autowired
    public AdminController(AdminService adminService, FoodItemService foodItemService){
        this.adminService = adminService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("/adminRegister")
    public String createAdminRegistrationForm(){
        return "admin/adminRegister";
    }

    @PostMapping("/adminRegister")
    public String createAdminAccount(Admin admin, Model model) {
        try {
            this.adminService.createAdmin(admin);
        }catch (Exception e){
            model.addAttribute("message", "signup_failed");
            System.out.println(e);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("admin", admin);
            return "admin/adminRegister";
        }
        return "redirect:adminLogin?message=signup_success";
    }
    @GetMapping("/adminLogin")
    public String adminLoginPage(
            @RequestParam(name = "message", required = false) String message,
            Model model
    ){
        model.addAttribute("message", message);
        return "admin/adminLogin";
    }

    @PostMapping("/adminLogin")
    public String handleAdminLogin(Admin admin, HttpServletResponse response){
        try{
            Admin loggedInAdmin = adminService.verifyAdmin(admin);
            Cookie cookie = new Cookie("adminId", loggedInAdmin.getId().toString());
            cookie.setMaxAge(7 * 24 * 60 * 60);

            response.addCookie(cookie);
            response.addCookie(new Cookie("adminIsLoggedIn", "true"));

            return "redirect:adminProfileView/"+ loggedInAdmin.getId();
        }catch (Exception e){

            return "redirect:adminLogin?message=login_failed&error=" +e.getMessage();
        }
    }

    @GetMapping("/adminProfileView/{adminId}")
    public String displayAdminPage(@PathVariable Long adminId, Model model, Admin admin){
        model.addAttribute("adminId", adminId);
        model.addAttribute(admin);
        return "admin/adminProfileView";
    }
////    WORKS!
//    @GetMapping("/adminProfileView/{adminId}/adminAddMeal")
//    public String displayAdminAddMeal(@PathVariable Long adminId, Model model){
//        model.addAttribute("id", adminService.findAdminById(adminId));
//        return "admin/adminAddMeal";
//    }
//
////    WORKS!
//    @PostMapping("/adminProfileView/{adminId}/adminAddMeal")
//    public String addNewFoodItem(FoodItem foodItem, @PathVariable ("adminId") Long adminId, Model model){
//        model.addAttribute("userId", adminId);
//        this.foodItemService.createFoodItem(foodItem);
//        return "redirect:/adminProfileView/" + adminId + "/adminAddMeal";
//    }
////    TRY METHODS FOR ADD MEAL FORM
////not working!!!!!!!!!!!!!!!!!!!!!
//    @GetMapping("/adminProfileView/{adminId}/adminViewAllMeal")
//    public String showAllMeal(@PathVariable Long adminId, Model model) {
//        model.addAttribute("adminId", adminId);
//        model.addAttribute("foodItemList", this.foodItemService.getAllFoodItems());
//        return "admin/adminViewAllMeal";
//    }
//
//    @GetMapping("/adminProfileView/{adminId}/adminViewAllMeal/delete/{foodItemId}")
//    public String deleteFoodItem(@PathVariable(name = "foodItemId") Long foodItemId, @PathVariable Long adminId, Model model) {
//        model.addAttribute("adminId", adminId);
//        this.foodItemService.deleteItemById(foodItemId);
//        return "redirect:/adminViewAllMeal?message=product_deleted";
//    }
//
//    @GetMapping("/adminProfileView/{adminId}/edit{foodItemId}")
//    public String showUpdateFoodItemForm(@PathVariable(name = "foodItemId") Long foodItemId, Model model, @PathVariable Long adminId) {
//        model.addAttribute("adminId", adminId);
//        model.addAttribute("foodItem", this.foodItemService.getAllFoodItemsById(foodItemId));
//        return "admin/adminUpdateMeal";
//    }


//    @PostMapping("/adminProfileView/{adminId}/updateMeal/{foodItemId}")
//    public String updateFoodItem(@PathVariable(name="foodItemId") Long foodItemId, FoodItem updatedFoodItem, @PathVariable Long adminId, Model model) {
//        this.adminService.findAdminById(adminId);
//        this.foodItemService.updateFoodItemById(foodItemId, updatedFoodItem);
//        this.foodItemService.createFoodItem(updatedFoodItem);
//        return "redirect:/adminProfileView/{adminId}/admin/adminViewAllMeal";
//    }

}
