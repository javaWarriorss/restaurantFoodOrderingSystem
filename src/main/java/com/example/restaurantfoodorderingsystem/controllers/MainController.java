package com.example.restaurantfoodorderingsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/menu")
    public String showMenu(){
        return "menu";
    }

    @GetMapping("/contacts")
    public String showContacts(){
        return "customer/contactUs";
    }

}
