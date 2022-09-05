package com.example.springandangular_group2_ams.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Article Management API")
                                .description("Spring Boot API made with Spring Data JPA, Kotlin and PostgreSQL. All endpoints are open and no security has been applied.")
                );
    }
}

