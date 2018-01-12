package com.poc.dynamicform.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.poc.dynamicform.domain.entity.DynamicForm;
import com.poc.dynamicform.web.form.Form;

@Component
public class DynamicFormEntityToForm implements Converter<DynamicForm, Form> {

	@Override
	public Form convert(final DynamicForm entity) {
		
		if(entity == null)
			return null;
		
		final Form form = new Form();
		form.setId(entity.getId());
		form.setName(entity.getName());
		form.setVersion(entity.getVersion());
		return form;
	}

}