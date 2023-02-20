package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.enums.PaymentMethod;
import com.example.restaurantfoodorderingsystem.classes.CheckOutInfo;
import com.example.restaurantfoodorderingsystem.entities.CartItem;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import com.example.restaurantfoodorderingsystem.services.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CheckoutController {
   private CheckoutService checkoutService;
   private CustomerService customerService;
   private CustomerAddressService customerAddressService;
   private CartItemService cartItemService;
   private OrderService orderService;

    public CheckoutController(CheckoutService checkoutService, CustomerService customerService, CustomerAddressService customerAddressService, CartItemService cartItemService, OrderService orderService) {
        this.checkoutService = checkoutService;
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
        this.cartItemService = cartItemService;
        this.orderService = orderService;
    }

    @GetMapping("menu/{customerId}/cart/order")
    public String showCheckoutPage(@PathVariable Long customerId, Model model) throws Exception {
        model.addAttribute("customerId",customerId);
        Customer customer=customerService.findCustomerById(customerId);
        List<CartItem> cartItems = cartItemService.listCartItems(customer);
        CheckOutInfo checkoutInfo = checkoutService.prepareCheckout(cartItems);

        CustomerAddress customerAddress=customerAddressService.findAllAddressById(customer.getId());
        model.addAttribute("cartItems",cartItems);
        model.addAttribute("shippingAddress",customerAddress.toString());
        model.addAttribute("customerName",customer.getFirstName()+" "+customer.getLastName());
        model.addAttribute("checkoutInfo",checkoutInfo);

        return "customer/checkoutView";
    }
    @Transactional
    @PostMapping("menu/{customerId}/cart/placeOrder")
    public String placeOrder(@PathVariable Long customerId, Model model,PaymentMethod paymentType) throws Exception {
        model.addAttribute("customerId",customerId);
        model.addAttribute("paymentType",paymentType);
        PaymentMethod paymentMethod = PaymentMethod.valueOf(String.valueOf(paymentType));
        Customer customer=customerService.findCustomerById(customerId);

        CustomerAddress customerAddress=customerAddressService.findAllAddressById(customer.getId());
        List<CartItem> cartItems = cartItemService.listCartItems(customer);
        CheckOutInfo checkoutInfo = checkoutService.prepareCheckout(cartItems);

       // model.addAttribute("cartItems",cartItems);
        orderService.createOrder(customer,customerAddress,cartItems,paymentMethod,checkoutInfo); // CREATES ORDER
        cartItemService.deleteByCustomer(customer); // DELETES CART BY CUSTOMER ID AFTER ORDER IS PLACED

        return "customer/orderCompleted";
    }
}
