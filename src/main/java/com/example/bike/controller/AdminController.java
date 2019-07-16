package com.example.bike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class AdminController {

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "/user/welcome";
    }

    @GetMapping("/bike_list")
    public String toList() {
        return "/bike/list";
    }

}
