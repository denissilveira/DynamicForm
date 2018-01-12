package com.poc.dynamicform.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.dynamicform.domain.entity.DynamicField;
import com.poc.dynamicform.domain.entity.DynamicOption;

@Repository
public interface DynamicOptionRepository extends CrudRepository<DynamicOption, Long> {
	
	List<DynamicOption> findByDynamicField(final DynamicField dynamicField);

}