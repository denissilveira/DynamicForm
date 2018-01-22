package com.poc.dynamicform.web.form;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, 
        include = JsonTypeInfo.As.PROPERTY, 
        property = "elementType")
      @JsonSubTypes({ 
        @Type(value = Field.class, name = "field"), 
        @Type(value = Group.class, name = "group") 
      })
@JsonInclude(Include.NON_NULL)
public class Element {
    
    
    protected Integer position;
    private List<Element> elements;
    
    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }
    public List<Element> getElements() {
        return elements;
    }
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}