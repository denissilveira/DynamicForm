package com.poc.dynamicform.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.domain.entity.DynamicForm;
import com.poc.dynamicform.domain.entity.DynamicGroup;
import com.poc.dynamicform.web.form.Element;
import com.poc.dynamicform.web.form.Form;

@Service
public class DynamicFormEntityToForm implements Converter<DynamicForm, Form> {
	
	@Autowired
	private GroupEntityToGroupForm groupConverter;
	@Autowired
	private FieldEntityToFieldForm fieldConverter;

	@Override
	public Form convert(final DynamicForm entity) {
		
		if(entity == null)
			return null;
		
		final Form form = new Form();
		form.setName(entity.getName());
		form.setVersion(entity.getVersion());
		form.setMethod(entity.getMethod());
		form.setShow(entity.isShow());
		form.setAction(entity.getAction());
		final List<Element> elements = new ArrayList<Element>();
		entity.getElements().forEach(element -> {
		    
		    if(element instanceof DynamicGroup) {
		        elements.add(groupConverter.convert((DynamicGroup)element));
		    } else if(element instanceof DynamicField) {
		        elements.add(fieldConverter.convert((DynamicField)element));
		    }
		    
		});
		form.setElements(elements);
		return form;
	}
	
}