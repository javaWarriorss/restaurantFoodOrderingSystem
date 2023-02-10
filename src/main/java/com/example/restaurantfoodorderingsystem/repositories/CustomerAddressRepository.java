package com.example.restaurantfoodorderingsystem.repositories;
import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CustomerAddressRepository extends CrudRepository<CustomerAddress,Integer> {
    CustomerAddress findAllById (Integer customerId);
    @Override
    ArrayList<CustomerAddress> findAll();
}
