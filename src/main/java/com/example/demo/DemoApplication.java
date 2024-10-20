package com.example.demo;

import com.example.demo.utis.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("");
		System.out.println("H2 DB:");
		System.out.println("http://localhost:8080/h2-console");
		System.out.println("");
		System.out.println("AUTHENTICATION");
		System.out.println("http://localhost:8080/api/v1/users/login");
		System.out.println("");
		System.out.println("API:");
		System.out.println("http://localhost:8080/api/v1/users");
		System.out.println("http://localhost:8080/api/v1/headObjects");
		System.out.println("http://localhost:8080/api/v1/subEntities");
		System.out.println("http://localhost:8080/api/v1/subEntities/110044");
	}

	@Bean
	public UUID uuidBean() {
		return new UUID();
	}

}
