package com.poc.dynamicform.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.poc.dynamicform.domain.entity.DynamicOption;
import com.poc.dynamicform.web.form.Option;

@Component
public class OptionEntityToOptionForm implements Converter<DynamicOption, Option> {

	@Override
	public Option convert(final DynamicOption entity) {
		if(entity == null)
			return null;
		
		final Option option = new Option();
		option.setOption(entity.getOption());
		option.setValue(entity.getValue());
		return option;
	}

}