package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.enums.PaymentMethod;
import com.example.restaurantfoodorderingsystem.classes.CheckOutInfo;
import com.example.restaurantfoodorderingsystem.entities.*;
import com.example.restaurantfoodorderingsystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    public Order createOrder(Customer customer, CustomerAddress customerAddress,
                             List<CartItem> cartItems, PaymentMethod paymentMethod,
                             CheckOutInfo checkOutInfo) {
        List<Order> listOfOrder =new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            FoodItem foodItem = cartItem.getFoodItem();
            Order newOrder = new Order();
            newOrder.setOrderTime(new Date());
            newOrder.setCustomer(customer);
//            newOrder.setProductCost(checkOutInfo.getProductTotal()); // cost for each product * quantity
//            newOrder.setSubtotal(checkOutInfo.getProductTotal()); // total cart price
            newOrder.setProductCost(foodItem.getPrice()); // cost for each product * quantity
            newOrder.setSubtotal(foodItem.getPrice()*cartItem.getQuantity()); // total cart price


            newOrder.setPaymentMethod(paymentMethod);

            newOrder.getShippingAddress(customerAddress);

            newOrder.setFoodItem(foodItem);
            newOrder.setQuantity(cartItem.getQuantity());

            listOfOrder.add(orderRepository.save(newOrder));
        }
        System.out.println(listOfOrder);
    return listOfOrder.get(0);
    }


}
