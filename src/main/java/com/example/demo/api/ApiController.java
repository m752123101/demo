package com.example.demo.api;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapping.Citydao;
import com.example.demo.mapping.bean.City;
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

    /**
     * http://localhost:8080/index
     * @return
     */
    @GetMapping("/index")
    public List index() {
        List<City> allCityInfo = citydao.getAllCityInfo();
        System.out.println("into index ");
        System.out.println(JSON.toJSON(allCityInfo));
        return allCityInfo;
//        System.out.println(this.getClass());
//        try {
//            //D:/other/test/demo/target/classes/
//            String path = ResourceUtils.getURL("classpath:").getPath();
//            System.out.println(path);
//        }catch (Exception e){
//
//        }
//        return null;
    }
}
