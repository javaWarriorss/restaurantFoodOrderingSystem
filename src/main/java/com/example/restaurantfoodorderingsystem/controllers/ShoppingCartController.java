package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.CartItem;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.services.CartItemService;
import com.example.restaurantfoodorderingsystem.services.CustomerService;
import com.example.restaurantfoodorderingsystem.services.FoodItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DecimalFormat;
import java.util.List;

@Controller
public class ShoppingCartController {
  //  @Autowired
    private CartItemService cartItemService;
    private CustomerService customerService;

    private FoodItemService foodItemService;

    public ShoppingCartController(CartItemService cartItemService, CustomerService customerService, FoodItemService foodItemService) {
        this.cartItemService = cartItemService;
        this.customerService = customerService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("menu/{customerId}/cart")

    public String showShoppingCart(@PathVariable Long customerId, Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) throws Exception {
        model.addAttribute("customerId",customerIdFromCookie);
        Customer customer=customerService.findCustomerById(Long.valueOf(customerIdFromCookie));
        List<CartItem> cartItems= cartItemService.listCartItems(customer);
        //calculates total price for all items in list
        Double totalAmount = 0.0;
        for(CartItem item:cartItems){
            totalAmount += item.getSubtotal();
            totalAmount =Double.parseDouble(new DecimalFormat("##.####").format(totalAmount));
        }


        model.addAttribute("cartItems",cartItems);
        model.addAttribute("totalAmount",totalAmount);
        return "customer/shoppingCart";
    }

@GetMapping("menu/{customerId}/{productId}/cart")
    public String showShoppingCart(@PathVariable Long customerId,@PathVariable("productId") Long productId, Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie) throws Exception {
    model.addAttribute("customerId",customerIdFromCookie);
    model.addAttribute("productId",productId);
   // model.addAttribute("quantity",quantity);
        Customer customer=customerService.findCustomerById(Long.valueOf(customerIdFromCookie));
        List<CartItem> cartItems= cartItemService.listCartItems(customer);

        //calculates total price for all items in list
        Double totalAmount = 0.0;
        for(CartItem item:cartItems){
            totalAmount += item.getSubtotal();
            totalAmount =Double.parseDouble(new DecimalFormat("##.####").format(totalAmount));
        }
        model.addAttribute("cartItems",cartItems);
        model.addAttribute("totalAmount",totalAmount);
       return "customer/shoppingCart";
    }
@PostMapping("menu/{customerId}/{productId}/cart")
    public String addProductToCart(@PathVariable ("customerId") Long customerId, @PathVariable("productId") Long productId ,  Model model,CartItem cartItem,@CookieValue(value = "customerCookie")String customerIdFromCookie){
    model.addAttribute("customerId",customerIdFromCookie);
    model.addAttribute("qtyValue", cartItem.getQuantity()); // need to get quantity from bar
    model.addAttribute("productId", productId);//  needs food id from path
    Customer customer = customerService.findAllCustomersById(Long.valueOf(customerIdFromCookie));
    cartItemService.addProduct(productId, cartItem.getQuantity(), customer);

  //  return "redirect:/menu/"+customerId+"/"+productId+"/cart";
    return "redirect:/menu/"+customerIdFromCookie+"/"+productId+"/cart";
}

    @PostMapping("menu/{customerId}/{productId}/cart/update")
    public String UpdateProductCartQuantity(@PathVariable ("customerId") Long customerId, @PathVariable("productId") Long productId ,  Model model,CartItem cartItem,@CookieValue(value = "customerCookie")String customerIdFromCookie){
        model.addAttribute("customerId",customerIdFromCookie);
        model.addAttribute("qtyValue",cartItem.getQuantity()); // need to get quantity from bar
        model.addAttribute("productId",productId);//  needs food id from path
        Customer customer =customerService.findAllCustomersById(Long.valueOf(customerIdFromCookie));
        cartItemService.updateCartProduct(productId,cartItem.getQuantity(),customer);

      //  return "redirect:/menu/"+customerId+"/"+productId+"/cart";
        return "redirect:/menu/"+customerIdFromCookie+"/"+productId+"/cart";
    }
@Transactional
    @PostMapping("menu/{customerId}/{productId}/cart/delete")
    public String DeleteItemInCartByid(@PathVariable ("customerId") Long customerId,@PathVariable("productId") Long productId ,Model model,@CookieValue(value = "customerCookie")String customerIdFromCookie){
    model.addAttribute("customerId",customerIdFromCookie);
        model.addAttribute("productId",productId);
        cartItemService.removeItemFromCart(Long.valueOf(customerIdFromCookie), productId);
        return "redirect:/menu/{customerId}/cart";
    }



}


















