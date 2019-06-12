package com.example.bike.controller;

import com.example.bike.dto.Bike;
import com.example.bike.service.BikeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    private BikeServiceImp service;

    @GetMapping
    public String checkBike(Bike bike) {
        System.out.println(bike);
        service.save(bike);
        return "success";
    }
}
