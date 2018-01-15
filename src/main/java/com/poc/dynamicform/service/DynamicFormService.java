package com.poc.dynamicform.service;

import com.poc.dynamicform.domain.entity.DynamicForm;
import com.poc.dynamicform.web.form.Form;

public interface DynamicFormService {
	
	Form loadForm(final Long id) throws Exception;
}