package com.kalaha.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kalaha.web")
public class KalahaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KalahaApplication.class, args);
	}

}
