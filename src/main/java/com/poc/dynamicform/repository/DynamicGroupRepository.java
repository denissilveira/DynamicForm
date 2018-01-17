package com.poc.dynamicform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.dynamicform.domain.entity.DynamicGroup;

@Repository
public interface DynamicGroupRepository extends CrudRepository<DynamicGroup, Long> {
	
	@Query(value = "select dg.* from DynamicGroup dg where dg.dinamicForm = ?1 and dg.parent is null", nativeQuery=true)
	List<DynamicGroup> findByDynamicFormAndIsFather(final Long dynamicFormId);
	
	List<DynamicGroup> findByParent(final DynamicGroup group);
}