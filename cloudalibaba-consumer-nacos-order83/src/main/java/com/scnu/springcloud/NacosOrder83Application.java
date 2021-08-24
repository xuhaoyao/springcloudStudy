package com.scnu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosOrder83Application {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrder83Application.class,args);
    }
}
