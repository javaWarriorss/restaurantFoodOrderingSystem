//package com.example.restaurantfoodorderingsystem.controllers;
//
//import com.example.restaurantfoodorderingsystem.entities.Admin;
//import com.example.restaurantfoodorderingsystem.entities.CartItem;
//import com.example.restaurantfoodorderingsystem.entities.Order;
//import com.example.restaurantfoodorderingsystem.services.AdminService;
//import com.example.restaurantfoodorderingsystem.services.CustomerService;
//import com.example.restaurantfoodorderingsystem.services.FoodItemService;
//import com.example.restaurantfoodorderingsystem.services.OrderService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Controller
//public class OrderController {
//    private OrderService orderService;
//    private CustomerService customerService;
//    private AdminService adminService;
//    public OrderController(OrderService orderService,
//                           AdminService adminService,
//                           CustomerService customerService){
//        this.customerService = customerService;
//        this.orderService = orderService;
//        this.adminService = adminService;
//    }
//
//    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
//    public ModelAndView getOrders(@PathVariable Long adminId, Model model) {
//        ModelAndView modelAndView = new ModelAndView(ordersView);
//
//        Admin admin = adminService.findAdminById(adminId);
//
//        List<Order> orders = new ArrayList<>(admin.getOrders());
//        orders.sort(Comparator.comparing(Order::getId).reversed());
//        modelAndView.addObject("orders", orders);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = {"/order/create"}, method = RequestMethod.GET)
//    public ModelAndView createOrder() {
//        ModelAndView modelAndView = new ModelAndView(ordersView);
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();
//        User user = userService.getUserByLogin(userName);
//
//        if (user.getCart().getItems().isEmpty()) {
//            return modelAndView;
//        }
//
//        Cart oldCart = user.getCart();
//        user.setCart(new Cart());
//
//        Order order = new Order();
//        order.setCart(oldCart);
//        order.setUser(user);
//
//        order.setStatus(Status.WAIT);
//        user.addOrder(order);
//
//        cartService.updateCart(oldCart);
//        orderService.addOrder(order);
//        userService.updateUser(user);
//
//        // send email for user
//        mailService.sendEmail(order);
//
//        List<Order> orders = new ArrayList<>(user.getOrders());
//        orders.sort(Comparator.comparing(Order::getId).reversed());
//        modelAndView.addObject("orders", orders);
//        return modelAndView;
//    }
//}
