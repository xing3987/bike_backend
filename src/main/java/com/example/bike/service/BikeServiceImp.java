package com.example.bike.service;

import com.example.bike.dto.Bike;
import com.example.bike.mapper.BikeMapper;
import com.sun.deploy.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class BikeServiceImp implements BikeService {

    @Autowired
    private BikeMapper mapper;
    @Autowired
    private MongoTemplate mongoTemplate; //mongo的模版
    @Value("${spring.data.mongodb.collection.bike}")
    private String collection;

    @Override
    public void save(Bike bike) {
        mapper.save(bike);
    }

    public void save(String bike) {
        mongoTemplate.save(bike, collection);
    }

    public List<Bike> getAllBike() {
        return mongoTemplate.findAll(Bike.class, collection);
    }
}
