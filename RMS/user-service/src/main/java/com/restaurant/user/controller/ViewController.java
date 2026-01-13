package com.restaurant.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // Login page
    @GetMapping("/")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage2() {
        return "login";
    }

    // Role-specific pages
    @GetMapping("/waiter-system")
    public String waiterSystem() {
        return "waiter-system";
    }

    @GetMapping("/chef-system")
    public String chefSystem() {
        return "chef-system";
    }

    @GetMapping("/cashier-system")
    public String cashierSystem() {
        return "cashier-system";
    }

    @GetMapping("/manager-system")
    public String managerSystem() {
        return "manager-system";
    }
}

