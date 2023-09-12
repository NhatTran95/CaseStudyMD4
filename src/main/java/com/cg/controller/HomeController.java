package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHomePage() {
        return "views/index";
    }

    @GetMapping("/booking")
    public String showBook() {
        return "views/index";
    }

}
