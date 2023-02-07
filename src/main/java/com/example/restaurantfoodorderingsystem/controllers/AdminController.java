package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.Admin;
import com.example.restaurantfoodorderingsystem.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final AdminService adminService;
    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/adminRegister")
    public String createAdminRegistrationForm(Model model){
        model.addAttribute("admin", new Admin());
        return "adminRegister";
    }

    @PostMapping("/adminRegister")
    public String createAdminAccount(@ModelAttribute Admin admin) throws Exception {
        this.adminService.createAdmin(admin);
        return "redirect:/adminLogin";
    }
    @GetMapping("/adminLogin")
    public String adminLoginPage(
            @RequestParam(name = "message", required = false) String message,
            Model model
    ){
        model.addAttribute("message", message);
        return "adminLogin";
    }

    @PostMapping("/adminLogin")
    public String handleAdminLogin(Admin admin){
        try{
            Admin loggedInAdmin = adminService.verifyAdmin(admin);
            return "redirect:adminProfileView/"+ loggedInAdmin.getAdminId();
        }catch (Exception e){

            return "redirect:login?message=login_failed&error=" +e.getMessage();
        }
    }

    @GetMapping("/adminProfileView/{adminId}")
    public String displayAdminPage(@PathVariable Long adminId, Model model){
        model.addAttribute("adminId", adminId);
        return "adminProfileView";
    }
}
