package com.spring;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.config.ResourceConfig;

@EnableAutoConfiguration
@SpringBootApplication
public class PetShopApplication {

	public static void main(String[] args) {
		new File(ResourceConfig.uploadDirectory + "user/").mkdir();
		new File(ResourceConfig.uploadDirectory + "user/small").mkdir();
		new File(ResourceConfig.uploadDirectory + "pet/").mkdir();
		new File(ResourceConfig.uploadDirectory + "pet/small/").mkdir();
		new File(ResourceConfig.uploadDirectory + "pet/medium").mkdir();
		new File(ResourceConfig.uploadDirectory + "product/").mkdir();
		new File(ResourceConfig.uploadDirectory + "product/small/").mkdir();
		new File(ResourceConfig.uploadDirectory + "product/medium/").mkdir();
		
		SpringApplication.run(PetShopApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PetShopApplication.class);
	}
	
//	@Bean
//	CommandLineRunner run() {
//		return args -> {
//			Algorithm alg = Algorithm.HMAC256("secret".getBytes());
//			BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
//			System.out.println(bcp.encode("administrator"));
//			
//		};
//	}
}
