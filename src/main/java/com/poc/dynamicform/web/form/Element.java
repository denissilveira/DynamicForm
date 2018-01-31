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
    
    private String name;
    protected Integer position;
    private boolean multiple;
    private boolean show;
    private List<Element> elements;
    private Long maxElements;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
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
    public boolean isMultiple() {
        return multiple;
    }
    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }
    public boolean isShow() {
        return show;
    }
    public void setShow(boolean show) {
        this.show = show;
    }
    public Long getMaxElements() {
        return maxElements;
    }
    public void setMaxElements(Long maxElements) {
        this.maxElements = maxElements;
    }

}