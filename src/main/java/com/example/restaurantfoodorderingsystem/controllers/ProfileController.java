package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import com.example.restaurantfoodorderingsystem.entities.Order;
import com.example.restaurantfoodorderingsystem.services.CustomerAddressService;
import com.example.restaurantfoodorderingsystem.services.CustomerService;
import com.example.restaurantfoodorderingsystem.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfileController {
    CustomerService customerService;
   CustomerAddressService customerAddressService;
   OrderService orderService;
@Autowired
    public ProfileController(CustomerService customerService, CustomerAddressService customerAddressService, OrderService orderService) {
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
        this.orderService = orderService;
    }

    // updating customer profile data
    @GetMapping("menu/{customerId}/profile")
    public String updateCustomerShow( @RequestParam(name="messageTwo", required = false)String messageTwo, Model model, @PathVariable  Long customerId) throws Exception {
       // model.addAttribute("customerId", customerId);
        model.addAttribute("customer",customerService.findAllCustomersById(customerId));
        model.addAttribute("messageTwo", messageTwo);
        return "customer/customerProfile";
    }

    @PostMapping("menu/{customerId}/profile")
    public String updateCustomer(@PathVariable(name="customerId")  Long customerId, Customer updatedCustomer,Model model){
        try {
            this.customerService.updateCustomerById(customerId, updatedCustomer);
            this.customerService.createCustomer(updatedCustomer);
        } catch (Exception e) {
            model.addAttribute("messageTwo", "emailUpdate_failed");
            model.addAttribute("error", e.getMessage());
            return "customer/customerProfile";
        }
        return "redirect:profile?messageTwo=update_success";
    }

    // updating customer address
    @GetMapping("menu/{customerId}/address")
    public String updateCustomerAddressShow(@RequestParam(name="messageForAddress", required = false)String messageForAddress, Model model, @PathVariable  Long customerId) throws Exception {
        model.addAttribute("customerAddress",customerAddressService.findAllAddressById(customerId));
        model.addAttribute("messageForAddress", messageForAddress);
        return "customer/customerAddress";
    }

    @PostMapping("menu/{customerId}/address")
    public String updateCustomerAddress(@PathVariable(name="customerId")  Long customerId, CustomerAddress updatedAddress,Model model){
     try {
        this.customerAddressService.updateCustomerAddressById(customerId, updatedAddress);
        this.customerAddressService.createCustomerAddress(updatedAddress);
    } catch (Exception e) {
        model.addAttribute("messageForAddress", "addressUpdate_failed");
        model.addAttribute("error", e.getMessage());
         return "customer/customerAddress";
    }
     return "redirect:address?messageForAddress=addressUpdate_success";
    }

    @GetMapping("menu/{customerId}/orderHistory")
    public String showOrderHistory(Model model, @PathVariable  Long customerId) throws Exception {
        model.addAttribute("customerId",customerId);
        Customer customer=customerService.findCustomerById(customerId);
        List<Order> orderList =  orderService.findOrderByCustomerId(customer.getId()); // query did not return a unique result: 18
        model.addAttribute("orderList",orderList);
        return "customer/orderHistory";
    }
}

