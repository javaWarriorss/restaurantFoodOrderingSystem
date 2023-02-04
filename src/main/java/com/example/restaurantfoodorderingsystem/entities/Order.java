package com.example.restaurantfoodorderingsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue()
    private Integer orderId;
    private Integer customerId;
    private Integer foodItemId;
    private Integer quantity;
    private Double price;
    @CreationTimestamp
    private Timestamp createdAt;
//    private Timestamp updatedAt;

}
