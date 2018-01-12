package com.poc.dynamicform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.poc.dynamicform.service.HelloWorldService;
import com.poc.dynamicform.web.form.HelloWordForm;

@Controller
public class HellorWorldController {
	
	@Autowired
	private HelloWorldService service;
	
	@GetMapping("/helloWorld")
	public String helloWorld(final Model model) {
		
		try {
			final HelloWordForm form = service.loadForm(1L);
			model.addAttribute("form", new Gson().toJson(form));
			System.out.println(new Gson().toJson(form));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/helloWorld";
	}

}

// {"id":1,"name":"Hello World","version":1,"groups":[{"id":1,"name":"Hello World Group","fields":[{"id":1,"type":{"id":1,"type":"input","subtype":"text"},"fname":"Hello World","flabel":"Hello World","fvalue":"Hello World","fshow":true,"frequired":true}]}]}