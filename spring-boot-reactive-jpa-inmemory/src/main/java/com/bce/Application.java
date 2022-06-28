package com.bce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bce.model.Employee;

import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
	@Bean("empoyeePublisher")
	public Many<Employee> sink(){
		return Sinks.many().replay().limit(5);
	}


}
