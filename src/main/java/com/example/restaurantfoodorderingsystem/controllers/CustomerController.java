package com.example.restaurantfoodorderingsystem.controllers;

import com.example.restaurantfoodorderingsystem.entities.Customer;
import com.example.restaurantfoodorderingsystem.entities.CustomerAddress;
import com.example.restaurantfoodorderingsystem.services.CustomerAddressService;
import com.example.restaurantfoodorderingsystem.services.CustomerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {
private final CustomerService customerService;
private  final CustomerAddressService customerAddressService;

    public CustomerController(CustomerService customerService, CustomerAddressService customerAddressService) {
        this.customerService = customerService;
        this.customerAddressService = customerAddressService;
    }
    @GetMapping("/register")
    public String showRegistrationPage(){
        return "customer/register";
    }
    @PostMapping("/register")
    public String handleCustomerRegistration(Customer customer, BindingResult result, Model model, CustomerAddress customerAddress) {
       try {
           Customer existingCustomer = customerService.findCustomerByEmail(customer.getEmail());
           if(existingCustomer != null && existingCustomer.getEmail() != null && !existingCustomer.getEmail().isEmpty()){
               result.rejectValue("email", null,
                       "There is already an account registered with the same email");
           }
            this.customerAddressService.createCustomerAddress(customerAddress);
            this.customerService.createCustomer(customer,customerAddress);
        }catch (Exception e){
            model.addAttribute("message","signup_failed");
            model.addAttribute("error",e.getMessage());
            model.addAttribute("customer",customer);
            return "customer/register";
        }
        return "redirect:login?message=signup_success";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model,
      @RequestParam(name="message", required = false)String message
    ){
        model.addAttribute("message", message);
        return "customer/login";
    }

    @PostMapping("/login")
    public String handleCustomerLogin(Customer customer, HttpServletResponse response){
        try {
            Customer loggedInCustomer =customerService.verifyCustomer(customer);
//                    HttpSession session= request.getSession(true);
//                    session.setAttribute("customerCookie",loggedInCustomer.getId().toString());
            Cookie cookie = new Cookie("customerCookie",loggedInCustomer.getId().toString());
            response.addCookie(cookie);





            return "redirect:menu/" +loggedInCustomer.getId();
        }catch (Exception e){
            return "redirect:/login?message=login_failed&error=" +e.getMessage();
        }
    }

    @GetMapping("/logout")
    public String logoutCustomerAndDeleteCookies(HttpServletResponse response){
        try {

            Cookie deleteServletCookie = new Cookie("customerCookie", null);
            deleteServletCookie.setMaxAge(0);
//            response.setHeader("Cache-Control","no-cache");
//            response.setHeader("Cache-Control","no-store");
//            response.setDateHeader("Expires", 0);
            response.addCookie(deleteServletCookie);

//
//            Cookie[] cookies = request.getCookies();
//            if(cookies != null){
//                for(Cookie cookie : cookies){
//                    if(cookie.getName().equals("JSESSIONID")){
//                        System.out.println("JSESSIONID="+cookie.getValue());
//                    }
//                    cookie.setMaxAge(0);
//                    response.addCookie(cookie);
//                }
//            }
//            //invalidate the session if exists
//            HttpSession session = request.getSession(false);
//            System.out.println("User="+session.getAttribute("customerCookie"));
//            if(session != null){
//                session.invalidate();
//            }
//            //no encoding because we have invalidated the session
//            response.sendRedirect("/login.html");


            return   "redirect:/";
        }catch (Exception e){
                return "redirect:/login";
        }
       // return "redirect:/login";

    }
}
