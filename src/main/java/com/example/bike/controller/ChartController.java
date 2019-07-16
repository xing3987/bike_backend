package com.example.bike.controller;

import com.example.bike.dto.Bike;
import com.example.bike.dto.ValueName;
import com.example.bike.dto.ZoneVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/charts")
@Controller
public class ChartController {

    @Autowired
    private MongoTemplate mongoTemplate; //mongo的模版

    @GetMapping("/posi")
    public String toPosi() {
        return "/charts/position";
    }

    @GetMapping("/map")
    public String tomap() {
        return "/charts/map";
    }

    @ResponseBody
    @GetMapping("/getPosiData")
    public ZoneVO getPosiData() {
        //List<Bike> bikes = mongoTemplate.findAll(Bike.class);
        ZoneVO vo = new ZoneVO();
        vo.setNames(Arrays.asList("海淀区", "昌平区", "东城区", "西城区"));
        List<ValueName> valueNameList = new ArrayList<ValueName>();
        valueNameList.add(new ValueName(666, "海淀区"));
        valueNameList.add(new ValueName(333, "昌平区"));
        valueNameList.add(new ValueName(567, "东城区"));
        valueNameList.add(new ValueName(456, "西城区"));
        vo.setValueNames(valueNameList);
        return vo;
    }
}
