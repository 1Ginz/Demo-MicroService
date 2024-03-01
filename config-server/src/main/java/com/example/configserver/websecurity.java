package com.example.configserver;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@EnableWebSecurity
public class websecurity extends WebSecurityConfiguration {

    protected void  configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().anyRequest().permitAll();
//        http.csrf().disable();
    }

}
