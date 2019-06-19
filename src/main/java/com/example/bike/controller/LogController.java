package com.example.bike.controller;

import com.example.bike.service.LogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogServiceImp service;

    @PostMapping("ready")
    public String checkBike(@RequestBody String readyLog) {
        System.out.println(readyLog);
        service.save(readyLog);
        return "success";
    }
}
