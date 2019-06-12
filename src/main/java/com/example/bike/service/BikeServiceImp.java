package com.example.bike.service;

import com.example.bike.dto.Bike;
import com.example.bike.mapper.BikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BikeServiceImp implements BikeService {

    @Autowired
    private BikeMapper mapper;

    @Override
    public void save(Bike bike) {
        mapper.save(bike);
    }
}
