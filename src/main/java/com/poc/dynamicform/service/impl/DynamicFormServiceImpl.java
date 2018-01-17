package com.poc.dynamicform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.poc.dynamicform.converter.DynamicFormEntityToForm;
import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.domain.entity.DynamicForm;
import com.poc.dynamicform.domain.entity.DynamicGroup;
import com.poc.dynamicform.repository.DynamicFieldRepository;
import com.poc.dynamicform.repository.DynamicFormRepository;
import com.poc.dynamicform.repository.DynamicGroupRepository;
import com.poc.dynamicform.repository.DynamicOptionRepository;
import com.poc.dynamicform.service.DynamicFormService;
import com.poc.dynamicform.web.form.Form;

@Service
public class DynamicFormServiceImpl implements DynamicFormService {
	
	@Autowired
	private DynamicFormRepository dfRepository;
	@Autowired
	private DynamicGroupRepository dgRepository;
	@Autowired
	private DynamicFieldRepository dfieldRepository;
	@Autowired
	private DynamicOptionRepository doRepository;
	@Autowired
	private DynamicFormEntityToForm formConverter;

	public Form loadForm(final Long id) throws Exception {

		final DynamicForm form = dfRepository.findOne(id);
		if (form == null)
			throw new Exception("Formulário não encontrado!");

		final List<DynamicGroup> groups = dgRepository.findByDynamicFormAndIsFather(form.getId());
		if (CollectionUtils.isEmpty(groups))
			throw new Exception("Grupos não encontrado!");

		groups.forEach(group -> {
			group.setGroups(getChildrenGroups(group));
			group.setFields(getFields(group));
		});

		form.setGroups(groups);
		return formConverter.convert(form);
	}

	public List<DynamicGroup> getChildrenGroups(final DynamicGroup group) {

		final List<DynamicGroup> groups = dgRepository.findByParent(group);
		if (!CollectionUtils.isEmpty(groups)) {
			groups.forEach(groupChield -> {
				groupChield.setGroups(getChildrenGroups(groupChield));
			});
		}
		group.setFields(getFields(group));
		return groups;
	}

	public List<DynamicField> getFields(final DynamicGroup group) {
		final List<DynamicField> fields = dfieldRepository.findByDynamicGroup(group);
		fields.forEach(field -> {
			field.setOptions(doRepository.findByDynamicField(field));
		});
		return fields;
	}


}