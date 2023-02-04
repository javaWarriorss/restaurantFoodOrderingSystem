package com.example.restaurantfoodorderingsystem.repositories;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Customer findCustomerByEmailAndPassword(String email, String password);
    @Override
    ArrayList<Customer> findAll();
}
