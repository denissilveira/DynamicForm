package com.poc.dynamicform.web.form;

import java.util.List;

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