package com.example.restaurantfoodorderingsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FoodItemController {

    @GetMapping("customerPageAfterLogin/{customerId}")
    public String displayPageAfterLogin(
            @PathVariable Integer customerId, Model model
    ){
        model.addAttribute("customerId",customerId);
        return "customerPageAfterLogin";
    }
}
