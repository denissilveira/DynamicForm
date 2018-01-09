package com.poc.dynamicform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.dynamicform.domain.entity.DynamicType;

@Repository
public interface DynamicTypeRepository extends CrudRepository<DynamicType, Long> {

}