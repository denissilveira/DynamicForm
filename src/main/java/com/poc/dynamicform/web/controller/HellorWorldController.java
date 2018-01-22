package com.poc.dynamicform.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
			final DynamicFormWrapper dfw = new DynamicFormWrapper(form);
			
			model.addAttribute("dynamicform", dfw);
			System.out.println(new Gson().toJson(form));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/helloWorld";
	}
	
	public class DynamicFormWrapper {

		private String form;

		public DynamicFormWrapper(final Form form) {
			ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            try {
                this.form = mapper.writeValueAsString(form);
            } catch (Exception e) {
                // TODO Auto-generated catch block
            } 
		}

		public String getForm() {
			return form;
		}

		public void setForm(String form) {
			this.form = form;
		}
	}

}
