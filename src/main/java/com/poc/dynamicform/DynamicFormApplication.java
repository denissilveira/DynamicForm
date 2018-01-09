package com.poc.dynamicform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.poc.dynamicform"})
public class DynamicFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicFormApplication.class, args);
	}
}
