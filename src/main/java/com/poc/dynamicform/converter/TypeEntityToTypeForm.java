package com.poc.dynamicform.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.poc.dynamicform.domain.entity.DynamicType;
import com.poc.dynamicform.web.form.Type;

@Component
public class TypeEntityToTypeForm implements Converter<DynamicType, Type> {

	@Override
	public Type convert(DynamicType entity) {

		if(entity == null)
			return null;

		final Type type = new Type();
		type.setSubtype(entity.getSubtype());
		type.setType(entity.getType());
		return type;
	}

}