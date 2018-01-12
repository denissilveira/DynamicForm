package com.poc.dynamicform.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.poc.dynamicform.domain.entity.DynamicGroup;
import com.poc.dynamicform.web.form.Group;

@Component
public class GroupEntityToGroupForm implements Converter<DynamicGroup, Group> {
	
	//@Autowired
	//private DynamicFormEntityToForm formConverter;

	@Override
	public Group convert(final DynamicGroup entity) {
		
		if(entity == null)
			return null;
		
		final Group group = new Group();
		group.setId(entity.getId());
		//group.setForm(formConverter.convert(entity.getDynamicForm()));
		group.setName(entity.getName());
		return group;
	}
}