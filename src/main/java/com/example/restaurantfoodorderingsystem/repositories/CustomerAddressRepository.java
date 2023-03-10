package com.example.restaurantfoodorderingsystem.repositories;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CustomerAddressRepository extends CrudRepository<CustomerAddress, Long> {
    CustomerAddress findAllById ( Long customerId);
    @Override
    ArrayList<CustomerAddress> findAll();
}
