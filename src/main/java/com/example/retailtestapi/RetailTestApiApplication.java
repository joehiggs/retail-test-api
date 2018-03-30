package com.example.retailtestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RetailTestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailTestApiApplication.class, args);
	}
}
