package com.example.restaurantfoodorderingsystem.repositories;

import com.example.restaurantfoodorderingsystem.entities.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findByIdAndPassword(Long id, String password);
//    Admin findAdminById(Long adminId);
    @Override
    ArrayList<Admin> findAll();
}
