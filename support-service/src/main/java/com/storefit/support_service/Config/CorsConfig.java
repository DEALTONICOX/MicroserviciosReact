package com.storefit.support_service.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
<<<<<<< HEAD
                                "http://localhost:5173", // Vite
                                "http://localhost:5174",
                                "http://localhost:3000"  // React clásico
=======
                                "http://localhost:5174",
                                "http://localhost:5173", 
                                "http://localhost:3000"  
>>>>>>> ab20ed77b27d6fe625f608acaee6cc05a0cabacc
                        )
                        .allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
