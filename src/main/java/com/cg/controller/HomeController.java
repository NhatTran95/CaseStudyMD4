package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "views/index";
    }
    @GetMapping("/services")
    public String showServicesPage() {
        return "views/services";
    }

    @GetMapping("/portfolio")
    public String showPortfolioPage() {
        return "views/portfolio";
    }



}
