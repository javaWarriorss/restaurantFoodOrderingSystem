package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.Admin;
import com.example.restaurantfoodorderingsystem.services.AdminService;
import com.example.restaurantfoodorderingsystem.services.FoodItemService;
import com.example.restaurantfoodorderingsystem.services.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class AdminController {
    @Autowired
    private final AdminService adminService;
    @Autowired
    private final FoodItemService foodItemService;

    private final OrderService orderService;


//
//    @Autowired
//    public AdminController(AdminService adminService, FoodItemService foodItemService) {
//        this.adminService = adminService;
//        this.foodItemService = foodItemService;
//    }

        public AdminController(AdminService adminService,FoodItemService foodItemService, OrderService orderService) {
        this.adminService = adminService;
        this.foodItemService = foodItemService;
        this.orderService = orderService;
    }


    @GetMapping("/adminRegister")
    public String createAdminRegistrationForm() {
        return "admin/adminRegister";
    }

    @PostMapping("/adminRegister")
    public String createAdminAccount(Admin admin, Model model) {
        try {
            this.adminService.createAdmin(admin);
        } catch (Exception e) {
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
    ) {
        model.addAttribute("message", message);
        return "admin/adminLogin";
    }

    @PostMapping("/adminLogin")
    public String handleAdminLogin(Admin admin, HttpServletResponse response) {
        try {
            Admin loggedInAdmin = adminService.verifyAdmin(admin);
            Cookie cookie = new Cookie("adminCookie", loggedInAdmin.getId().toString());

            response.addCookie(cookie);

            return "redirect:adminProfileView/" + loggedInAdmin.getId();
        } catch (Exception e) {
            return "redirect:adminLogin?message=login_failed&error=" + e.getMessage();
        }
    }
    @GetMapping("/adminProfileView/{adminId}/updateProfile")
    public String updateAdminPasswordForm(@PathVariable Long adminId, @CookieValue("adminCookie") String adminIdCookie, Model model, @RequestParam(name = "messageForPasswordUpdate", required = false) String message){
        model.addAttribute("adminId", adminIdCookie);
        model.addAttribute("admin", adminService.findAdminById(Long.valueOf(adminIdCookie)));
        model.addAttribute("messageForPasswordUpdate", message);
        return "admin/adminProfileUpdate";
    }

    @PostMapping("/adminProfileView/{adminId}/updateProfile")
    public String updateAdmin(@PathVariable Long adminId, Model model, @CookieValue(value = "adminCookie") String adminIdCookie, Admin updatedAdmin) throws Exception {
        try {
            model.addAttribute("adminId", adminIdCookie);
            this.adminService.updateAdmin(Long.valueOf(adminIdCookie), updatedAdmin);
            this.adminService.createAdmin(updatedAdmin);
        }catch (Exception e) {
            model.addAttribute("messageForPasswordUpdate","password_update_failed");
            model.addAttribute("error", e.getMessage());
            return "redirect:/adminProfileView/{adminId}/updateProfile/";
        }
        return "redirect:updateProfile?messageForPasswordUpdate=password_updated";
    }



//    @GetMapping("/adminProfileView/{adminId}")
//    public String displayAdminPage(@PathVariable Long adminId, Model model, Admin admin) {
//        model.addAttribute("adminId", adminId);
//        model.addAttribute("admin", admin);
//        return "admin/adminProfileView";
//    }


    // Get orders by today's date

    @GetMapping("/adminProfileView/{adminId}")
    public String displayAdminPage(@PathVariable Long adminId, Model model, Admin admin) {
        model.addAttribute("adminId", adminId);
        model.addAttribute("admin", admin);
        model.addAttribute("orderList", this.orderService.getOrdersByDate());
        return "admin/adminProfileView";
    }



    @GetMapping("/adminLogout")
    public String adminLogout(HttpServletResponse response) {
        try {
            Cookie deleteCookie = new Cookie("adminCookie", null);
            deleteCookie.setMaxAge(0);
            response.addCookie(deleteCookie);
            return "redirect:/adminLogin";
        } catch (Exception e) {
            return "redirect:/adminLogin";
        }
    }
}