package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.CartItem;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.repositories.CartItemRepository;
import com.example.restaurantfoodorderingsystem.repositories.CustomerRepository;
import com.example.restaurantfoodorderingsystem.repositories.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartItemService {

   private final CartItemRepository cartItemRepository;
   private final FoodItemRepository foodItemRepository;
    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, FoodItemRepository foodItemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.foodItemRepository = foodItemRepository;
    }

    public List<CartItem> listCartItems(Customer customer){
        return cartItemRepository.findByCustomer(customer);
    }

    //puts item to shopping cart,
    // return quantity of product what has been added to shopping cart
    public Integer addProduct(Long productId, Integer quantity, Customer customer){
        Integer addedQuantity =quantity;
        FoodItem foodItem= foodItemRepository.findById(productId).get();
        CartItem cartItem =cartItemRepository.findByCustomerAndFoodItem(customer,foodItem);

        if(cartItem!=null){ // if cart item is not null it menans product is in cart and we need increase quantity
            addedQuantity =cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        }else { // when product is not added in cart for this customer
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setCustomer(customer);
            cartItem.setFoodItem(foodItem);
        }
            cartItemRepository.save(cartItem); // we save items to repository/sql ?
        return addedQuantity;
    }

    public Integer updateCartProduct(Long productId, Integer quantity, Customer customer){
        Integer addedQuantity =quantity;
        FoodItem foodItem= foodItemRepository.findById(productId).get();
        CartItem cartItem =cartItemRepository.findByCustomerAndFoodItem(customer,foodItem);

        if(cartItem!=null){ // SHOULD WE NEED THIS LINE ?
            addedQuantity =cartItem.getQuantity();
            cartItem.setQuantity(quantity);
        }
        cartItemRepository.save(cartItem); // we save items to repository/sql ?
        return addedQuantity;
    }
    public void removeItemFromCart (Long customer, Long productId){
        System.out.println("seervice:"+customer+productId);
       cartItemRepository.deleteCartItemByCustomer_IdAndFoodItem_Id(customer, productId);
    }
public void deleteByCustomer(Customer customer){
        cartItemRepository.deleteByCustomerId(customer.getId());
}

}
