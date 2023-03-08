package com.greedy.security.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.greedy.security")
public class Chap03SecuritySessionLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap03SecuritySessionLoginApplication.class, args);
	}

}
