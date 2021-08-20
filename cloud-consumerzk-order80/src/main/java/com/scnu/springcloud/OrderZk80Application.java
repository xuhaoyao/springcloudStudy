package com.scnu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderZk80Application {
    public static void main(String[] args) {
        SpringApplication.run(OrderZk80Application.class,args);
    }
}
