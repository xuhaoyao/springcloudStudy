package com.scnu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamConsumer8802Application {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumer8802Application.class, args);
    }

}