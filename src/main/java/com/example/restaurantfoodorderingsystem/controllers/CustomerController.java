package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import com.example.restaurantfoodorderingsystem.services.CustomerAddressService;
import com.example.restaurantfoodorderingsystem.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
private final CustomerService customerService;
private  final CustomerAddressService customerAddressService;

    public CustomerController(CustomerService customerService, CustomerAddressService customerAddressService) {
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
    }

    @GetMapping("/register")
    public String showRegistrationPage(){
        return "register";
    }

    // REPAIR NEEDED: IT SENDS CUSTOMERADDRESS ANYWAY IF EMAILS ARE THE SAME,
    // BUT CUSTOMER DATA IS NOT SENDING IN TABLE WHICH IS CORRECT IN CASE WHEN EMAILS ARE THE SAME
    @PostMapping("/register")
    public String handleCustomerRegistration( Model model,Customer customer, CustomerAddress customerAddress){
        try {
            this.customerAddressService.createCustomerAddress(customerAddress);
            this.customerService.createCustomer(customer,customerAddress);
        }catch (Exception e){
            model.addAttribute("message","signup_failed");
            model.addAttribute("error",e.getMessage());
            model.addAttribute("customer",customer);
            return "register";
        }

        return "redirect:login?message=signup_success";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model,
      @RequestParam(name="message", required = false)String message
    ){
        model.addAttribute("message", message);

        return "login";
    }


    @PostMapping("/login")
    public String handleUserLogin(Customer customer){
        try {
            Customer loggedInCustomer =customerService.verifyCustomer(customer);
            return "redirect:customerPageAfterLogin/" +loggedInCustomer.getId();

        }catch (Exception e){
            return "redirect:login?message=login_failed&error=" +e.getMessage();
        }  

    }


}
