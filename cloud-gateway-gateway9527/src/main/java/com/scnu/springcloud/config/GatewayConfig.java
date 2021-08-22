package com.scnu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    /**
     * 访问localhost:9527/vip的时候,会拼接https://wenku.baidu.com 和 /vip
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("route_baidu",r -> r.path("/vip")
                        .uri("https://wenku.baidu.com"))
                .build();
    }
}
