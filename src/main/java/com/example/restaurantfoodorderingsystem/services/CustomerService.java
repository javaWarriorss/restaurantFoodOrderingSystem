package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import com.example.restaurantfoodorderingsystem.repositories.CustomerAddressRepository;
import com.example.restaurantfoodorderingsystem.repositories.CustomerRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }
    public void createCustomer(Customer customer, CustomerAddress customerAddress) throws Exception {
        try {
            customer.setCustomerAddress(customerAddress);
            this.customerRepository.save(customer);

        } catch (Exception e) {
                    throw new Exception("Email already exists");
        }
    }

    public void createCustomer(Customer customer) throws Exception {
        try {
            this.customerRepository.save(customer);

        } catch (Exception e) {
            throw new Exception("Email already exists");
        }
    }

    public Customer updateCustomerById( Long id, Customer updatedCustomer) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setDateOfBirth(updatedCustomer.getDateOfBirth());
        customer.setPhoneNumber(customer.getPhoneNumber());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPassword(updatedCustomer.getPassword());
        customer.setCustomerAddress(updatedCustomer.getCustomerAddress());

            if(customer.getEmail().equals(updatedCustomer.getEmail())){
                throw new Exception("this email is already taken");
            }
        return customerRepository.save(customer);
    }

    public  Customer verifyCustomer(Customer customerLoginRequest) throws Exception{
        Customer foundCustomer=this.customerRepository.findCustomerByEmailAndPassword(customerLoginRequest.getEmail(),customerLoginRequest.getPassword());
        if (foundCustomer == null){
            throw new Exception("Email or password incorrect");
        }
        return foundCustomer;
    }


    public Customer findCustomerById( Long customerId) throws Exception{
        return  this.customerRepository.findById(customerId).orElseThrow();
    }

    public Customer findCustomerByEmail(String customerEmail) throws Exception{
        return  this.customerRepository.findCustomerByEmail(customerEmail);
    }
        public Customer findAllCustomersById (Long customerId){
                return  this.customerRepository.findAllById(customerId);
        }
}
