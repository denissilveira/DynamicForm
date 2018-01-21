package com.poc.dynamicform.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.poc.dynamicform.domain.entity.DynamicElement;
import com.poc.dynamicform.web.form.Element;

@Component
public class ElementEntityToElementForm implements Converter<DynamicElement, Element>{

    @Override
    public Element convert(final DynamicElement entity) {
        
        if(entity == null)
            return null;
        
        final Element element = new Element();
        element.setPosition(entity.getPosition());
        return element;
    }

}