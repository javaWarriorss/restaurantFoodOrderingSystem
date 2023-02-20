package com.example.restaurantfoodorderingsystem.entities;

import com.example.restaurantfoodorderingsystem.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderTime;
    private Double productCost;
    private Double subtotal;
    @ManyToOne
    @JoinColumn (name = "customer_id_fk",foreignKey = @ForeignKey(name = "customer_id")) // was nessasary to add fk name because had error for duplicate  from other table?
    private  Customer customer; // customer can place one or more orders
    private String street;
    private String city;
    private String country;
    private String postCode;
    @Enumerated (EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Integer quantity;
    @ManyToOne
    @JoinColumn (name = "foodItem_id",foreignKey = @ForeignKey(name = "foodItem_id_fk"))
    private FoodItem foodItem; // fooditem can have one or more orders

    public void getShippingAddress(CustomerAddress customerAddress) {
        setStreet(customerAddress.getStreet());
        setCity(customerAddress.getCity());
        setCountry(customerAddress.getCountry());
        setPostCode(customerAddress.getPostCode());

    }

}
