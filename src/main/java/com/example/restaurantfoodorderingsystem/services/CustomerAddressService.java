package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import com.example.restaurantfoodorderingsystem.repositories.CustomerAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressService {
    private CustomerAddressRepository customerAddressRepository;

    @Autowired

    public CustomerAddressService(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }
    public void createCustomerAddress(CustomerAddress customerAddress) throws Exception {
        this.customerAddressRepository.save(customerAddress);
    }
    public CustomerAddress updateCustomerAddressById( Long id, CustomerAddress updatedAddress) {
        CustomerAddress customerAddress = customerAddressRepository.findById(id).orElse(null); // this will return object, which may not contain a food item, so we check
        if (customerAddress == null) {
            return null;
        }
        customerAddress.setStreet(updatedAddress.getStreet());
        customerAddress.setCity(updatedAddress.getCity());
        customerAddress.setCountry(updatedAddress.getCountry());
        customerAddress.setPostCode(updatedAddress.getPostCode());

        return customerAddressRepository.save(customerAddress);
    }

    public CustomerAddress findAllAddressById( Long id){
        return customerAddressRepository.findAllById(id);
    }
}
