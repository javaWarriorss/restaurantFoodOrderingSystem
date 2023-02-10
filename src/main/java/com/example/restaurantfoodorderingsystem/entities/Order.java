package com.example.restaurantfoodorderingsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private Timestamp createdAt;
    private Integer totalAmount;
    //    @OneToMany
//    @ForeignKey
//    private Customer customerId;
    private boolean status;
    //    status: confirmed/pending??
    @OneToMany
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<CartItem> cartItems;
}
