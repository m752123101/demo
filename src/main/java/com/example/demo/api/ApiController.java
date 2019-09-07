package com.example.demo.api;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapping.Citydao;
import com.example.demo.mapping.TestMapping;
import com.example.demo.mapping.bean.City;
import com.example.demo.mapping.bean.Test;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hanwen.dong
 * @date 2019/8/3 08:54
 * @Description auto
 */

@RestController
public class ApiController {
    @Autowired
    private Citydao citydao;

    @GetMapping("/index")
    public List index() {
        List<City> allCityInfo = citydao.getAllCityInfo();
        System.out.println("into index ");
        System.out.println(JSON.toJSON(allCityInfo));
        return allCityInfo;
    }
}
