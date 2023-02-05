package com.example.restaurantfoodorderingsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // for empty constructor
@AllArgsConstructor // for constructor
@Data // for getters and setters
@Entity // connecting fields with database

public class FoodItem {
    @Id // mark class as entity
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String foodName;
    private String description;
    private Integer price;
    private String category; // for example breakfast, dinner, special offer etc.
    private String type; // salads, pancakes, main dishes
    private boolean inStock;
    private String foodPictureUrl;


}