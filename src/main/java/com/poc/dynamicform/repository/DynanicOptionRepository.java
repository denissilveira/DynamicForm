package com.poc.dynamicform.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.domain.entity.DynanicOption;

@Repository
public interface DynanicOptionRepository extends CrudRepository<DynanicOption, Long> {
	
	List<DynanicOption> findByDynamicField(final DynamicField dynamicField);

}