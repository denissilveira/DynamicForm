package com.poc.dynamicform.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String indexEmpty() {
		return "/index";
	}

	@GetMapping("/home")
	public String home() {
		return "/home";
	}

	@GetMapping("/index")
	public String index() {
		return "/index";
	}
}