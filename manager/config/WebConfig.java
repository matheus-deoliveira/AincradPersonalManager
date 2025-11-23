package com.aincrad.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registery) {
        registry.addMapping("/**") // Libera tudo
                .allowedOrigins("http://localhost:4200") // Apenas para o Angular local
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}