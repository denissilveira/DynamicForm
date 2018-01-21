package com.poc.dynamicform.repository;

import org.springframework.transaction.annotation.Transactional;

import com.poc.dynamicform.domain.entity.DynamicElement;

@Transactional
public interface DynamicElementRepository extends ElementBaseRepository<DynamicElement> {
    
}