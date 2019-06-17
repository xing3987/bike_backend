package com.example.bike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/bike_list")
    public String toList() {
        return "/bike/list";
    }
}
