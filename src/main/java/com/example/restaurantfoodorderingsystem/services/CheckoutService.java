package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.classes.CheckOutInfo;
import com.example.restaurantfoodorderingsystem.entities.CartItem;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class CheckoutService {

    public CheckOutInfo prepareCheckout(List<CartItem> cartItems){
        CheckOutInfo checkOutInfo = new CheckOutInfo();

        Double productCost = calculateProductCost(cartItems);
        checkOutInfo.setProductCost(productCost);
        Double productTotal = calculateProductTotal(cartItems);
        checkOutInfo.setProductTotal(productTotal);
        return checkOutInfo;
    }

    private Double calculateProductTotal(List<CartItem> cartItems) {
// calculate all cart item total cost
        Double total = 0.0;
        for(CartItem item:cartItems) {
            total += item.getSubtotal();
            total =Double.parseDouble(new DecimalFormat("##.####").format( total));
        }
        return total;
    }

    private Double calculateProductCost(List<CartItem> cartItems) {
// calculates item total cost
        Double cost = 0.0;
        for(CartItem item:cartItems) {
            cost += item.getFoodItem().getPrice();
//            cost += item.getQuantity() * item.getFoodItem().getPrice();
            cost=Double.parseDouble(new DecimalFormat("##.####").format(cost));
        }
        return cost;
    }


}
