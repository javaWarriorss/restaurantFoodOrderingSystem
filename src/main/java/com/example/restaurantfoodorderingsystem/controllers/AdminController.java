package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.Admin;
import com.example.restaurantfoodorderingsystem.services.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
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

            response.addCookie(cookie);
            response.addCookie(new Cookie("adminIsLoggedIn", "true"));

            return "redirect:adminProfileView/"+ loggedInAdmin.getId();
        }catch (Exception e){

            return "redirect:login?message=login_failed&error=" +e.getMessage();
        }
    }

    @GetMapping("/adminProfileView/{adminId}")
    public String displayAdminPage(@PathVariable Long adminId, Model model, Admin admin){
        model.addAttribute("adminId", adminId);
        model.addAttribute(admin);
        return "admin/adminProfileView";
    }
}
