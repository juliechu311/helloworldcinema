package com.helloworidcinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.controller","com.service","com.dao","com.util","com.entity"})
@EnableJpaRepositories(basePackages = "com.dao")
public class HelloworidcinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloworidcinemaApplication.class, args);
	}

}
