package com.example.springandangular_group2_ams.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfiguration implements WebMvcConfigurer {


    //path that store image
    String serverPath = "/src/main/resources/images/";



    //Add handlers to serve static resources such as images, js, and, css files from specific locations
    // under web application root, the classpath, and others.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+serverPath);
    }




}


