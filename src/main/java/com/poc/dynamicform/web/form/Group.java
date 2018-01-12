package com.poc.dynamicform.web.form;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Group {
	
	private Long id;
	private Form form;
	private String name;
	private List<Field> fields;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Field> getFields() {
		return fields == null ? new ArrayList<Field>() : fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}