package com.scnu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderFeign80Application {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeign80Application.class,args);
    }
}
