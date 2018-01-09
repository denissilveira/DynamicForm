package com.poc.dynamicform.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.dynamicform.domain.entity.DynamicForm;
import com.poc.dynamicform.domain.entity.DynamicGroup;

@Repository
public interface DynamicGroupRepository extends CrudRepository<DynamicGroup, Long> {
	
	List<DynamicGroup> findByDynamicForm(final DynamicForm dynamicForm);
}