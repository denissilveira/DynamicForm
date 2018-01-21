package com.poc.dynamicform.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.poc.dynamicform.domain.entity.DynamicElement;
import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.domain.entity.DynamicGroup;
import com.poc.dynamicform.web.form.Element;
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
		group.setElements(getSubElements(entity));
		return group;
	}
	
	
	private List<Element> getSubElements(final DynamicElement parent) {
        
	    final List<Element> subElements = new ArrayList<Element>();
        if (!CollectionUtils.isEmpty(parent.getElements())) {
            parent.getElements().forEach(element -> {
                if(element instanceof DynamicGroup) {
                    subElements.add(convert((DynamicGroup) element));
                } else  if(element instanceof DynamicField) {
                    subElements.add(fieldConverter.convert((DynamicField) element));
                }
            });
        }
        
        return subElements;
    }


//	private List<Group> getChildren(final DynamicGroup entity) {
//		
//		final List<Group> groups = new ArrayList<Group>();
//		entity.getGroups().forEach(group -> {
//			groups.add(convert(group));
//		});
//		return groups;
//	}
	
//	public List<Field> getFields(final DynamicGroup entity) {
//		final List<Field> fields = new ArrayList<Field>();
//		entity.getFields().forEach(field -> {
//			fields.add(fieldConverter.convert(field));
//		});
//		return fields;
//	}
}