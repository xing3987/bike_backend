package com.example.bike.service;

import com.example.bike.dto.Bike;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BikeService {
    public void save(Bike bike);
}
