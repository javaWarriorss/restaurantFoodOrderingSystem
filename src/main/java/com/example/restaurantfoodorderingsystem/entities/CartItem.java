//package com.example.restaurantfoodorderingsystem.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class CartItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    @ManyToOne
//    private Customer customer;
//    @ManyToOne
//    private FoodItem foodItem;
//    private Integer quantity;
//
//    @Override
//    public String toString() {
//        return "CartItem{" +
//                "id=" + id +
//                ", customer=" + customer +
//                ", foodItem=" + foodItem +
//                ", quantity=" + quantity +
//                '}';
//    }
//}
