package com.example.restaurantfoodorderingsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Timestamp createdAt;
    private Integer totalAmount;
    @OneToOne
    private Customer customer;
    private Boolean status;
    //    status: confirmed/pending??
    @OneToOne
    private CartItem cartItems;
}
