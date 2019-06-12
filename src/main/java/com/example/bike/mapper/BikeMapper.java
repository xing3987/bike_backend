package com.example.bike.mapper;

import com.example.bike.dto.Bike;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BikeMapper {
    public void save(Bike bike);
}
