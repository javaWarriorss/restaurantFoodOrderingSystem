package com.example.restaurantfoodorderingsystem.services;

import com.example.restaurantfoodorderingsystem.entities.Admin;
import com.example.restaurantfoodorderingsystem.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public void createAdmin(Admin admin) throws Exception{
        this.adminRepository.save(admin);
    }

    public Admin verifyAdmin(Admin adminLoginRequest) throws Exception{
        Admin foundAdmin = this.adminRepository.findByIdAndPassword(adminLoginRequest.getId(), adminLoginRequest.getPassword());
        if(foundAdmin == null) {throw new Exception("Username or password incorrect");}

        return foundAdmin;
    }

    public Admin findAdminById(Long id){
        return this.adminRepository.findById(id).orElseThrow();
    }

    public Admin updateAdmin(Long id, Admin updatedAdmin) throws Exception{
        Admin admin = adminRepository.findById(id).orElseThrow();

        admin.setFirstName(updatedAdmin.getFirstName());
        admin.setLastName(updatedAdmin.getLastName());
        admin.setEmail(updatedAdmin.getEmail());
        admin.setPassword(updatedAdmin.getPassword());

        return adminRepository.save(admin);
    }
}
