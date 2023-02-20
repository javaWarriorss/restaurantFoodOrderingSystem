//package com.example.restaurantfoodorderingsystem.entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
////    @OneToOne(fetch = FetchType.LAZY)
////    private Customer customer;
//    @OneToMany
//    private List<CartItem> cartItems;
//    private String note;
//    @CreationTimestamp
//    private Timestamp createdAt;
////    @UpdateTimestamp
//////    private Timestamp updatedAt;
////    private boolean orderStatus;
////private String payment;
//}
