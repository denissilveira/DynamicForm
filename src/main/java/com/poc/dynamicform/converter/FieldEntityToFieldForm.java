package com.poc.dynamicform.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.web.form.Field;

@Component
public class FieldEntityToFieldForm implements Converter<DynamicField, Field>{

	@Autowired
	private TypeEntityToTypeForm typeConverter;
	
	@Override
	public Field convert(final DynamicField entity) {
		
		if(entity == null)
			return null;
		
		final Field field = new Field();
		//field.setGroup(group);
		field.setId(entity.getId());
		field.setType(typeConverter.convert(entity.getDynamicType()));
		return field;
	}

}
