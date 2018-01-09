package com.poc.dynamicform.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HellorWorldController {
	
	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "/helloWorld";
	}

}
