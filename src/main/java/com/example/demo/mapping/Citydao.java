package com.example.demo.mapping;/**
 * @title Citydao
 * @Description TODO
 * @Date 2019/9/7 18:22
 * @Created by hp
 */

import com.example.demo.mapping.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 注解方式
 *
 * @Auther: hanwen.dong
 * @Date: 2019/9/7 18:22
 * @Description: auto
 */
public interface Citydao {
    @Insert(
            "insert into city (name,countryCode,district,population) values (#{name, jdbcType=VARCHAR},#{countryCode, jdbcType=VARCHAR},#{district, jdbcType=VARCHAR}, #{population, jdbcType=INTEGER})"
    )
    void
    insert(City city);

    @Select(
            "select * from city"
    )
    List<City> getAllCityInfo();
}
