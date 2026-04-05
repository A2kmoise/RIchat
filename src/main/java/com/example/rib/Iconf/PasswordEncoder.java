package com.example.rib.Iconf;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncoder {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
