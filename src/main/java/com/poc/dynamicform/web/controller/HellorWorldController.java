package com.poc.dynamicform.web.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.poc.dynamicform.service.DynamicFormService;
import com.poc.dynamicform.web.form.Form;

@Controller
@RequestMapping(value = "/DynamicForm")
public class HellorWorldController {
	
	@Autowired
	private DynamicFormService service;
    
    @RequestMapping(value = "/Index", method = RequestMethod.GET)
    public String indexGet(final Model model) {

        Form form= null;

        try {
            
            form = service.loadForm(1L);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        final DynamicFormWrapper dfw = new DynamicFormWrapper(form);
        model.addAttribute("dynamicform", dfw);
        System.out.println(dfw.getForm());
        return "/DynamicForm/Index";
    }
    

    /**
     * 
     * @param dynamicform
     * @param model
     * @return
     */
    @RequestMapping(value = "/Index", method = RequestMethod.POST)
    public String indexPost(@ModelAttribute DynamicFormWrapper dynamicform, final Model model) {
        try {
            model.addAttribute("dynamicform", dynamicform);
            ObjectMapper mapper = new ObjectMapper();
            Form form = mapper.readValue(dynamicform.getForm(), Form.class);
            HashMap<String, Object> dataMap  = new ObjectMapper().readValue(dynamicform.getData(), HashMap.class);
            service.generateXML(form,dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/DynamicForm/Index";
    }
    
    
    // TODO REVER ESTA SOLUCAO COM A EQUIPE
    public static class DynamicFormWrapper {
        
        private String form;
        private String data;
        
        public DynamicFormWrapper(final Form form) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            try {
                this.form = mapper.writeValueAsString(form);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }
        
        public DynamicFormWrapper() {}

        public String getForm() {
            return form;
        }
        public void setForm(String form) {
            this.form = form;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

}
