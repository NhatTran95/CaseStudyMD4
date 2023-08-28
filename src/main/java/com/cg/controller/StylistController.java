package com.cg.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class StylistController {
    @GetMapping
    public String showListPage() {
        return "stylist/list";
    }
}
