package com.example.restaurantfoodorderingsystem.entities;
//KRISTINE

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String country;
    private String postCode;

    @Override
    public String toString() {
        return " "+street.substring(0,1).toUpperCase()+street.substring(1)+", "
                +city.substring(0,1).toUpperCase()+city.substring(1)+", "
                +country.substring(0,1).toUpperCase()+country.substring(1)+", "
                +postCode.substring(0,1).toUpperCase()+postCode.substring(1);



//                + '\n' +city + '\n'+ country + '\n' + postCode;
    }
}
