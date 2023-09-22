package com.cg.controller;

import com.cg.service.bookingService.BookingService;
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

    private final BookingService bookingService;

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
    public ModelAndView showServicePage(){
        ModelAndView view = new ModelAndView("views/services");
        view.addObject("hairDetails", hairDetailService.getAll());
        return view;
    }

    @GetMapping("/portfolio")
    public ModelAndView showProtfolioPage() {
        ModelAndView view = new ModelAndView("views/portfolio");
        view.addObject("stylists", stylistService.getAll());
        return view;

    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin/index";
    }



    @GetMapping("/stylist")
    public String showStylistPage() {
        return "admin/stylist";

    }

    @GetMapping("/serviceHair")
    public String showServiceHairPage() {
        return "admin/service";

    }





}
