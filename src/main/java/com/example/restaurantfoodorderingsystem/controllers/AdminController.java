package com.example.restaurantfoodorderingsystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {
    @GetMapping("/admin")
    public String showAdminLoginPage(){
        return "admin";
    }
}
