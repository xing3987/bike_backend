package com.example.bike.service;

import com.example.bike.dto.Bike;
import com.example.bike.mapper.BikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceImp implements BikeService {

    @Autowired
    private BikeMapper mapper;
    @Autowired
    private MongoTemplate mongoTemplate; //mongo的模版

    @Override
    public void save(Bike bike) {
        mapper.save(bike);
    }

    public void save(String bike) {
        mongoTemplate.save(bike);
    }

    public List<Bike> getAllBike() {
        return mongoTemplate.findAll(Bike.class);
    }
}
