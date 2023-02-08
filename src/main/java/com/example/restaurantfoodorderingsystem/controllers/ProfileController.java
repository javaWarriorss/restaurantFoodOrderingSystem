package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import com.example.restaurantfoodorderingsystem.repositories.CustomerRepository;
import com.example.restaurantfoodorderingsystem.services.CustomerService;
import jakarta.persistence.PostUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
public class ProfileController {
CustomerService customerService;

    public ProfileController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping ("menuAfterLogin/{customerId}/profile")
    public String updateCustomer(Model model, @PathVariable Integer customerId) throws Exception {
        model.addAttribute("customerId", customerId);
        return "customerProfile";
    }
    @PostMapping("menuAfterLogin/{customerId}/profile")
    public String updateCustomer(Customer customer,Model model, @PathVariable Integer customerId) throws Exception {
        model.addAttribute("customerId", customerService.findCustomerById(customerId));
        Customer existingCustomer =customerService.findCustomerById(customerId);

        if(existingCustomer!=null) {
            existingCustomer.setId(customer.getId());
            customer.setFirstName(existingCustomer.getFirstName());
            customer.setLastName(existingCustomer.getLastName());
            customer.setDateOfBirth(existingCustomer.getDateOfBirth());
            customer.setPhoneNumber(existingCustomer.getPhoneNumber());
            customer.setPassword(existingCustomer.getPassword());
            customer.setCustomerAddress(existingCustomer.getCustomerAddress());
            System.out.println(customerId.toString());
        }
            customerService.createCustomer(customer);
            System.out.println(customer.toString());
        return "customerProfile";
    }
}
