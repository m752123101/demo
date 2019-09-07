package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * @description AUTO
 * @author hanwen.dong
 * @date 2019/8/3 9:07
 */
@SpringBootApplication
@MapperScan(basePackages = "com.example.demo.mapping")
@EnableBatchProcessing
public class DemoApplication{

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
