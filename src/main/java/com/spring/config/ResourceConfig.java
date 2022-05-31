package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ResourceConfig implements WebMvcConfigurer {
	
	public static final String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/";
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//      registry
//      .addResourceHandler("/resources/**")
//      .addResourceLocations("/resources/");	
    //System.getProperty("user.dir") + "/src/main/resources/static"
    
    registry.addResourceHandler("/resources/**")
    .addResourceLocations("classpath:/static/");
    }
    

}
