package com.example.restaurantfoodorderingsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//KRISTINE
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Integer phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne
    private CustomerAddress customerAddress;

}
