package com.poc.dynamicform.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.poc.dynamicform.domain.entity.DynamicForm;
import com.poc.dynamicform.web.form.Form;
import com.poc.dynamicform.web.form.Group;

@Component
public class DynamicFormEntityToForm implements Converter<DynamicForm, Form> {
	
	@Autowired
	private GroupEntityToGroupForm groupConverter;

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
		final List<Group> groups = new ArrayList<Group>();
		entity.getGroups().forEach(group -> {
			groups.add(groupConverter.convert(group));
		});
		form.setGroups(groups);
		return form;
	}

}