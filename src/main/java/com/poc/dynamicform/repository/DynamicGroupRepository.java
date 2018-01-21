package com.poc.dynamicform.repository;

import org.springframework.transaction.annotation.Transactional;

import com.poc.dynamicform.domain.entity.DynamicGroup;

@Transactional
public interface DynamicGroupRepository extends ElementBaseRepository<DynamicGroup> {
	
}