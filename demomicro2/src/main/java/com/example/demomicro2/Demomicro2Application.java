package com.example.demomicro2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Demomicro2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demomicro2Application.class, args);
    }

}
