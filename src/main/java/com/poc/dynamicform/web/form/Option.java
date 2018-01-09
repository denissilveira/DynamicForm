package com.poc.dynamicform.web.form;

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