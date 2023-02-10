package com.example.restaurantfoodorderingsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
    //    private Integer orderId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    @OneToOne
    @PrimaryKeyJoinColumn(name = "foodItem_id", referencedColumnName = "id")
    private FoodItem foodItem;
}
