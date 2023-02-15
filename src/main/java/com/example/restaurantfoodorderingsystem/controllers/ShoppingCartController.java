package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.CartItem;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.services.CartItemService;
import com.example.restaurantfoodorderingsystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShoppingCartController {
  //  @Autowired
    private CartItemService cartItemService;
    private CustomerService customerService;

    public ShoppingCartController(CartItemService cartItemService, CustomerService customerService) {
        this.cartItemService = cartItemService;
        this.customerService = customerService;
    }

//    @GetMapping("menu/{customerId}/cart")
@GetMapping("menu/{customerId}/{productId}/cart")
    public String showShoppingCart(@PathVariable Long customerId,@PathVariable("productId") Long productId, Model model) throws Exception {
    model.addAttribute("customerId",customerId);
    model.addAttribute("productId",productId);
   // model.addAttribute("quantity",quantity);
        Customer customer=customerService.findCustomerById(customerId);
        List<CartItem> cartItems= cartItemService.listCartItems(customer);
        model.addAttribute("cartItems",cartItems);

       return "customer/shoppingCart";
    }
//    @PostMapping("menu/{customerId}/")
@PostMapping("menu/{customerId}/{productId}/cart")
    public String addProductToCart(@PathVariable ("customerId") Long customerId, @PathVariable("productId") Long productId ,  Model model,CartItem cartItem){
    System.out.println(cartItem.toString());
        model.addAttribute("customerId",customerId);
        model.addAttribute("qtyValue",cartItem.getQuantity()); // need to get quantity from bar
        model.addAttribute("productId",productId);//  needs food id from path
        Customer customer =customerService.findAllCustomersById(customerId);
       cartItemService.addProduct(productId,cartItem.getQuantity(),customer);

    return "redirect:/menu/"+customerId+"/"+productId+"/cart";
}





}


















