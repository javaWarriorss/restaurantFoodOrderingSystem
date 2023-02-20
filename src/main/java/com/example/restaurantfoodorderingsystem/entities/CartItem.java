package com.example.restaurantfoodorderingsystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.text.DecimalFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private FoodItem foodItem;
    private Integer quantity;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", customer=" + customer +
                ", foodItem=" + foodItem +
                ", quantity=" + quantity +
                '}';
    }

    public Double getSubtotal(){
     Double price =  this.foodItem.getPrice() * this.quantity;
        return  price =Double.parseDouble(new DecimalFormat("##.####").format(price));

    }

}
