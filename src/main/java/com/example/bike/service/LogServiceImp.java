package com.example.bike.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImp implements LogService {
    @Autowired
    private MongoTemplate mongoTemplate; //mongo的模版
    @Value("${spring.data.mongodb.collection.log}")
    private String collection;

    @Override
    public void save(String logs) {
        mongoTemplate.save(logs, collection);
    }
}
