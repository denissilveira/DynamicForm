package com.poc.dynamicform.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.web.form.Field;
import com.poc.dynamicform.web.form.Option;

@Component
public class FieldEntityToFieldForm implements Converter<DynamicField, Field>{

	@Autowired
	private TypeEntityToTypeForm typeConverter;
	@Autowired
	private OptionEntityToOptionForm optionConverter;
	
	@Override
	public Field convert(final DynamicField entity) {
		
		if(entity == null)
			return null;
		
		final Field field = new Field();
		field.setType(typeConverter.convert(entity.getDynamicType()));
		field.setAction(entity.getAction());
		field.setActionType(entity.getActionType());
		field.setLabel(entity.getLabel());
		field.setName(entity.getName());
		field.setRequired(entity.isRequired());
		field.setShow(entity.isShow());
		field.setShowLabel(entity.isShowLabel());
		field.setStyle(entity.getStyle());
		field.setValue(entity.getValue());
		final List<Option> options = new ArrayList<Option>();
		entity.getOptions().forEach(option -> {
			options.add(optionConverter.convert(option));
		});
		field.setOptions(options);
		return field;
	}

}
