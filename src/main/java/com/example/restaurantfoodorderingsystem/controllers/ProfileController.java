package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import com.example.restaurantfoodorderingsystem.repositories.CustomerRepository;
import com.example.restaurantfoodorderingsystem.services.CustomerAddressService;
import com.example.restaurantfoodorderingsystem.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {
    CustomerService customerService;
   CustomerAddressService customerAddressService;
   CustomerRepository customerRepository;

    public ProfileController(CustomerService customerService, CustomerAddressService customerAddressService) {
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
    }

    // updating customer profile data
    @GetMapping("menuAfterLogin/{customerId}/profile")
    public String updateCustomerShow(Model model, @PathVariable Integer customerId) throws Exception {
       // model.addAttribute("customerId", customerId);
        model.addAttribute("customer",customerService.findAllCustomersById(customerId));
        return "customer/customerProfile";
    }


    @PostMapping("menuAfterLogin/{customerId}/profile")
    public String updateCustomer(@PathVariable(name="customerId") Integer customerId,Customer updatedCustomer) throws Exception {
        this.customerService.updateCustomerById(customerId, updatedCustomer);
        this.customerService.createCustomer(updatedCustomer);
        return "customer/customerProfile";
    }

    // updating customer address
    @GetMapping("menuAfterLogin/{customerId}/address")
    public String updateCustomerAddressShow(Model model, @PathVariable Integer customerId) throws Exception {
        model.addAttribute("customerAddress",customerAddressService.findAllAddressById(customerId));
        return "customer/customerAddress";
    }


    @PostMapping("menuAfterLogin/{customerId}/address")
    public String updateCustomerAddress(@PathVariable(name="customerId") Integer customerId, CustomerAddress updatedAddress) throws Exception {
      // this.customerService.findCustomerById(customerId);
        this.customerAddressService.updateCustomerAddressById(customerId, updatedAddress);
        this.customerAddressService.createCustomerAddress(updatedAddress);
        return "customer/customerAddress";
    }
}

