package com.poc.dynamicform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.poc.dynamicform.converter.DynamicFormEntityToForm;
import com.poc.dynamicform.domain.entity.DynamicElement;
import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.domain.entity.DynamicForm;
import com.poc.dynamicform.domain.entity.DynamicGroup;
import com.poc.dynamicform.repository.DynamicElementRepository;
import com.poc.dynamicform.repository.DynamicFormRepository;
import com.poc.dynamicform.repository.DynamicOptionRepository;
import com.poc.dynamicform.service.DynamicFormService;
import com.poc.dynamicform.web.form.Form;

@Service
public class DynamicFormServiceImpl implements DynamicFormService {
	
	@Autowired
	private DynamicFormRepository dfRepository;
	@Autowired
	private DynamicOptionRepository doRepository;
	@Autowired
	private DynamicFormEntityToForm formConverter;
	@Autowired
    private DynamicElementRepository deRepository;

	public Form loadForm(final Long id) throws Exception {

		final DynamicForm form = dfRepository.findOne(id);
		if (form == null)
			throw new Exception("Formulário não encontrado!");
		
		final List<DynamicElement> dynamicElementsDB = deRepository.findByDynamicFormAndParentIsNullOrderByPosition(form);
		if (CollectionUtils.isEmpty(dynamicElementsDB))
            throw new Exception("Este Formulário não possui elementos!");
		
		final List<DynamicElement> elements = new ArrayList<DynamicElement>();
		dynamicElementsDB.forEach(element -> {
		    
		    if(element instanceof DynamicGroup) {
		        element.setElements(getSubElements(element));
		        elements.add(element);
		    } else  if(element instanceof DynamicField) {
		        final DynamicField dynamicField = (DynamicField) element;
		        dynamicField.setOptions(doRepository.findByDynamicField(dynamicField));
		        elements.add(dynamicField);
		    }
		});
		
		form.setElements(elements);
		
		return formConverter.convert(form);
	}
	
	private List<DynamicElement> getSubElements(final DynamicElement parent) {
	    
	    final List<DynamicElement> children = deRepository.findByParentOrderByPosition(parent);
	    
	    if (!CollectionUtils.isEmpty(children)) {
	        children.forEach(element -> {
	            if(element instanceof DynamicGroup) {
	                element.setElements(getSubElements(element));
	            } else  if(element instanceof DynamicField) {
	                final DynamicField dynamicField = (DynamicField) element;
	                dynamicField.setOptions(doRepository.findByDynamicField(dynamicField));
	            }
	        });
	    }
	    return children;
	}

}