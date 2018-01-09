package com.poc.dynamicform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.dynamicform.domain.entity.DynamicForm;

@Repository
public interface DynamicFormRepository extends CrudRepository<DynamicForm, Long> {

}