package com.poc.dynamicform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;
import com.poc.dynamicform.service.DynamicFormService;
import com.poc.dynamicform.web.form.Form;

@Controller
public class HellorWorldController {
	
	@Autowired
	private DynamicFormService service;
	
	@GetMapping("/helloWorld")
	public String helloWorld(final Model model) {
		
		try {
			final Form form = service.loadForm(1L);
			model.addAttribute("form", new Gson().toJson(form));
			System.out.println(new Gson().toJson(form));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/helloWorld";
	}

}
