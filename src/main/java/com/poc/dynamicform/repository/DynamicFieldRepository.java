package com.poc.dynamicform.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.domain.entity.DynamicGroup;

@Repository
public interface DynamicFieldRepository extends CrudRepository<DynamicField, Long> {
	
	List<DynamicField> findByDynamicGroup(final DynamicGroup dynamicGroup);

}