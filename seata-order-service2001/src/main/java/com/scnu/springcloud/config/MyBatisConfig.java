package com.scnu.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.scnu.springcloud.dao"})
public class MyBatisConfig {
}
