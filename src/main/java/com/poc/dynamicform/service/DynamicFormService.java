package com.poc.dynamicform.service;

import java.util.HashMap;

import com.poc.dynamicform.web.form.Form;

public interface DynamicFormService {
	
	Form loadForm(final Long id) throws Exception;
	
	void generateXML(com.poc.dynamicform.web.form.Form form,HashMap<String, Object> dataMap);
}