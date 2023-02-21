package com.example.restaurantfoodorderingsystem.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckOutInfo {
    private Double productCost;
    private Double productTotal;
//    private Double paymentTotal;

}
