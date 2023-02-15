package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.CartItem;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.FoodItem;
import com.example.restaurantfoodorderingsystem.repositories.CartItemRepository;
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

//    public Integer addProduct(Long productId,Integer quantity, Customer customerId){
//        Integer addedQuantity =quantity;
//        FoodItem foodItem= foodItemRepository.findById(productId).get();
//        CartItem cartItem =cartItemRepository.findByCustomerAndFoodItem(customerId,foodItem);
//
//        if(cartItem!=null){ // if cart item is not null it menans product is in cart and we need increase quantity
//            addedQuantity =cartItem.getQuantity() + quantity;
//            cartItem.setQuantity(addedQuantity);
//        }else { // when product is not added in cart for this customer
//            cartItem = new CartItem();
//            cartItem.setQuantity(quantity);
//            cartItem.setCustomer(customerId);
//            cartItem.setFoodItem(foodItem);
//        }
//        cartItemRepository.save(cartItem); // we save items to repository/sql ?
//
//        return addedQuantity;
//    }





//    private EntityManager entityManager;
//    public void addOneCartItem(Customer customerId, FoodItem foodItemId, Integer quantity){
//        Customer customer = entityManager.find(Customer.class,customerId);
//        FoodItem foodItem = entityManager.find(FoodItem.class,foodItemId);
//
//        CartItem newItem = new CartItem();
//        newItem.setCustomer(customer);
//        newItem.setFoodItem(foodItem);
//        newItem.setQuantity(quantity);
//        CartItem savedCartItem = cartItemRepository.save(newItem);
//
//    }
//
//    public void testGetCartItemsByCustomer( Long customerId){
//        Customer customer = new Customer();
//        customer.setId(customerId);
//        List<CartItem> cartItems = cartItemRepository.findByCustomer(customer);
//    }
}
