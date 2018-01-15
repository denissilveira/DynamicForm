package com.poc.dynamicform.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.poc.dynamicform.domain.entity.DynamicGroup;
import com.poc.dynamicform.web.form.Field;
import com.poc.dynamicform.web.form.Group;

@Component
public class GroupEntityToGroupForm implements Converter<DynamicGroup, Group> {
	
	@Autowired
	private FieldEntityToFieldForm fieldConverter;
	
	@Override
	public Group convert(final DynamicGroup entity) {
		
		if(entity == null)
			return null;
		
		final Group group = new Group();
		group.setName(entity.getName());
		group.setShow(entity.isShow());
		group.setShowName(entity.isShowName());
		group.setType(entity.getType());
		final List<Field> fields = new ArrayList<Field>();
		entity.getFields().forEach(field -> {
			fields.add(fieldConverter.convert(field));
		});
		// TODO ADD SUBGROUPS
		group.setFields(fields);
		return group;
	}
}