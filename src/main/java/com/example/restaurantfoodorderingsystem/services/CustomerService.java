package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import com.example.restaurantfoodorderingsystem.repositories.CustomerAddressRepository;
import com.example.restaurantfoodorderingsystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public  Customer verifyCustomer(Customer customerLoginRequest) throws Exception{
        Customer foundCustomer=this.customerRepository.findCustomerByEmailAndPassword(customerLoginRequest.getEmail(),customerLoginRequest.getPassword());
        if (foundCustomer == null){
            throw new Exception("Email or password incorrect");
        }
        return foundCustomer;
    }


    public Customer findCustomerById(Integer customerId) throws Exception{
        return  this.customerRepository.findById(customerId).orElseThrow();
    }

    public Customer findCustomerByEmail(String customerEmail) throws Exception{
        return  this.customerRepository.findCustomerByEmail(customerEmail);
    }

//   public Customer updateCustomerById(Integer customerId){
//        return this.customerRepository.
//
//    }
}
