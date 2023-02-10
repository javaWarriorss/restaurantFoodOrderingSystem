package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import com.example.restaurantfoodorderingsystem.repositories.CustomerRepository;
import com.example.restaurantfoodorderingsystem.services.CustomerAddressService;
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
   CustomerAddressService customerAddressService;

    public ProfileController(CustomerService customerService, CustomerAddressService customerAddressService) {
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
    }

    // updating customer profile data
    @GetMapping("menuAfterLogin/{customerId}/profile")
    public String updateCustomerShow(Model model, @PathVariable Integer customerId) throws Exception {
       // model.addAttribute("customerId", customerId);
        model.addAttribute("customer",customerService.findAllCustomersById(customerId));
        return "customerProfile";
    }


    @PostMapping("menuAfterLogin/{customerId}/profile")
    public String updateCustomer(@PathVariable(name="customerId") Integer customerId, Customer updatedCustomer) throws Exception {
        this.customerService.updateCustomerById(customerId, updatedCustomer);
        this.customerService.createCustomer(updatedCustomer);
        return "customerProfile";
    }

    // updating customer address
    @GetMapping("menuAfterLogin/{customerId}/address")
    public String updateCustomerAddressShow(Model model, @PathVariable Integer customerId) throws Exception {
        model.addAttribute("customerAddress",customerAddressService.findAllAddressById(customerId));
        return "customerAddress";
    }


    @PostMapping("menuAfterLogin/{customerId}/address")
    public String updateCustomerAddress(@PathVariable(name="customerId") Integer customerId, CustomerAddress updatedAddress) throws Exception {
      // this.customerService.findCustomerById(customerId);

        this.customerAddressService.updateCustomerAddressById(customerId, updatedAddress);
        this.customerAddressService.createCustomerAddress(updatedAddress);
        return "customerAddress";
    }
}

