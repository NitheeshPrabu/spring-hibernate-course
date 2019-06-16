package com.example.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Combines @EnableAutoConfiguration, @ComponentScan, and @Configuration
@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {

		// Creates application context, registers beans, starts embedded server, etc..
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}
