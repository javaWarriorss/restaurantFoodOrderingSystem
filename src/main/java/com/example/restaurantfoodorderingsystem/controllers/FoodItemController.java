package com.example.restaurantfoodorderingsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FoodItemController {

    @GetMapping("menuAfterLogin/{customerId}")
    public String displayPageAfterLogin(
            @PathVariable Integer customerId, Model model){
        model.addAttribute("customerId",customerId);
        return "menuAfterLogin";
    }
    @GetMapping("menuAfterLogin/{customerId}/contactUs")
    public String displayContactUs(@PathVariable Integer customerId, Model model){
        model.addAttribute("customerId",customerId);
        return "contactUs";
    }
}
