package com.poc.dynamicform.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.poc.dynamicform.domain.entity.DynamicElement;
import com.poc.dynamicform.domain.entity.DynamicForm;

@NoRepositoryBean
public interface ElementBaseRepository<T extends DynamicElement> extends CrudRepository<T, Long> {
    
    //@Query(value = "select de.* from #{#entityName} de where de.dynamicForm = :form and de.parent is null order by de.position", nativeQuery=true)
    public List<T> findByDynamicFormAndParentIsNullOrderByPosition(final DynamicForm form); 
    
    //@Query(value = "select de.* from #{#entityName} de where de.parent = :parent order by de.position", nativeQuery=true)
    public List<T> findByParentOrderByPosition(final DynamicElement parent);


}