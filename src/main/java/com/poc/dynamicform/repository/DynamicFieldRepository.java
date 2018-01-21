package com.poc.dynamicform.repository;

import org.springframework.transaction.annotation.Transactional;

import com.poc.dynamicform.domain.entity.DynamicField;

@Transactional
public interface DynamicFieldRepository extends ElementBaseRepository<DynamicField> {
	
}