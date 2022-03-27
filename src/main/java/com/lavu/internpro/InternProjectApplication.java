package com.lavu.internpro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.lavu.internpro.service.StorageService;
import com.lavu.internpro.utils.StorageProperties;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableConfigurationProperties(StorageProperties.class)
public class InternProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args -> {
			storageService.init();
		});
	}
}
