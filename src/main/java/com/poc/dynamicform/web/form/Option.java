package com.poc.dynamicform.web.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Option {
	
	private Long id;
	private String doption;
	private String dvalue;
	private Field field;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDoption() {
		return doption;
	}
	public void setDoption(String doption) {
		this.doption = doption;
	}
	public String getDvalue() {
		return dvalue;
	}
	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}

}