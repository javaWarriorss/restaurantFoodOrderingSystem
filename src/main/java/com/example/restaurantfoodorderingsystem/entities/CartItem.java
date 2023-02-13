package com.example.restaurantfoodorderingsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    private Long id;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private FoodItem foodItem;
    private Integer quantity;
}
