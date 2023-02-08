package com.example.restaurantfoodorderingsystem.repositories;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Customer findCustomerByEmailAndPassword(String email, String password);
    Customer findCustomerByEmail(String email);
    @Override
    ArrayList<Customer> findAll();


}
