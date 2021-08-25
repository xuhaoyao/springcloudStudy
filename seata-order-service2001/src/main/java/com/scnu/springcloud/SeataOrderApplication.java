package com.scnu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //取消数据源自动创建的配置
@EnableDiscoveryClient
@EnableFeignClients
public class SeataOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderApplication.class,args);
    }
}
