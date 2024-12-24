package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo.repositories.jpa")
public class BookHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookHubApplication.class, args);
	}

}
