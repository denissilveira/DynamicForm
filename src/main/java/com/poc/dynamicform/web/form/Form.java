package com.poc.dynamicform.web.form;

import java.util.ArrayList;
import java.util.List;

public class Form {
	
	private String name;
	private Integer version;
	private String method;
	private String action;
	private boolean show;
	List<Element> elements;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
    public List<Element> getElements() {
        return elements == null ? new ArrayList<Element>() : elements;
    }
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}