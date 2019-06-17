package com.example.bike.controller;

import com.example.bike.dto.Bike;
import com.example.bike.service.BikeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public String saveBike(@RequestBody String bike) {
        System.out.println(bike);
        //得到json数据，直接存入mongoDB
        service.save(bike);
        return "success";
    }

    @GetMapping("/all")
    public List<Bike> getAllBike() {
        return service.getAllBike();
    }
}
