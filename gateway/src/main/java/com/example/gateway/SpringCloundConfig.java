package com.example.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloundConfig {

    @Bean
    public RouteLocator buildRouteLocator(RouteLocatorBuilder builder){
        return builder.routes().route(r -> r.path("/price/**").uri("http://localhost:8889"))
                .route(r -> r.path("/invent/**").uri("http://localhost:9221"))
                .build();
    }

}
