package com.poc.dynamicform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.poc.dynamicform.converter.FieldEntityToFieldForm;
import com.poc.dynamicform.converter.GroupEntityToGroupForm;
import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.domain.entity.DynamicForm;
import com.poc.dynamicform.domain.entity.DynamicGroup;
import com.poc.dynamicform.repository.DynamicFieldRepository;
import com.poc.dynamicform.repository.DynamicFormRepository;
import com.poc.dynamicform.repository.DynamicGroupRepository;
import com.poc.dynamicform.service.HelloWorldService;
import com.poc.dynamicform.web.form.Field;
import com.poc.dynamicform.web.form.Group;
import com.poc.dynamicform.web.form.HelloWordForm;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	
	@Autowired
	private DynamicFormRepository dfRepository;
	@Autowired
	private DynamicGroupRepository dgRepository;
	@Autowired
	private DynamicFieldRepository dfieldRepository;
	//@Autowired
	//private DynamicOptionRepository doRepository;
	@Autowired
	private GroupEntityToGroupForm groupConverter;
	@Autowired
	private FieldEntityToFieldForm fieldConverter;
	
	
	@Override
	public HelloWordForm loadForm(final Long id) throws Exception {
		
		final DynamicForm form = dfRepository.findOne(id);
		if(form == null)
			throw new Exception("Formulário não encontrado!");
		
		final List<DynamicGroup> groups = dgRepository.findByDynamicForm(form);
		if(CollectionUtils.isEmpty(groups)) 
			throw new Exception("Grupos não encontrado!");
		
		final HelloWordForm helloForm = new HelloWordForm();
				
		helloForm.setId(form.getId());
		helloForm.setName(form.getName());
		helloForm.setVersion(form.getVersion());
		
		
		final List<Group> groupsForm = new ArrayList<Group>();
		for (DynamicGroup goup : groups) {
			
			final Group group = groupConverter.convert(goup);
			
			final List<DynamicField> fields = dfieldRepository.findByDynamicGroup(goup);
			if(CollectionUtils.isEmpty(fields)) 
				throw new Exception("Fields não encontrado!");
			
			final List<Field> fieldsForm = new ArrayList<Field>();
			for (DynamicField dynamicField : fields) {
				fieldsForm.add(fieldConverter.convert(dynamicField));
			}
			group.setFields(fieldsForm);
			groupsForm.add(group);
		}
		
		helloForm.setGroups(groupsForm);
		
		return helloForm;
	}

}