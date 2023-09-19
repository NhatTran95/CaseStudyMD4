package com.cg.controller;

import com.cg.service.hairDetailService.HairDetailService;
import com.cg.service.stylistService.StylistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class HomeController {
    private final HairDetailService hairDetailService;

    private final StylistService stylistService;

    @GetMapping("/home")
    public String showHomePage() {
        return "views/index";
    }


    @GetMapping("/booking")
    public ModelAndView showBookingPage() {
        ModelAndView view = new ModelAndView("views/booking");
        view.addObject("hairDetails", hairDetailService.findAll());
        view.addObject("stylists", stylistService.findAll());
        return view;
    }

    @GetMapping("/services")
    public String showServicePage() {
        return "views/services";
    }

    @GetMapping("/portfolio")
    public String showProtfokioPage() {
        return "views/portfolio";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin/index";
    }


    @GetMapping("/login")
    public String showLogin() {
        return "/login";
    }

    @GetMapping("/stylist")
    public String showStylistPage() {
        return "admin/stylist";

    }


}
