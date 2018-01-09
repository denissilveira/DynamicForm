package com.poc.dynamicform.web.form;

import java.util.List;

public class Field {
	
	private Long id;
	private Type type;
	private Group group;
	private String fname;
	private String flabel;
	private String fvalue;
	private String fstyle;
	private boolean fshow;
	private boolean frequired;
	private List<Option> options;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFlabel() {
		return flabel;
	}
	public void setFlabel(String flabel) {
		this.flabel = flabel;
	}
	public String getFvalue() {
		return fvalue;
	}
	public void setFvalue(String fvalue) {
		this.fvalue = fvalue;
	}
	public String getFstyle() {
		return fstyle;
	}
	public void setFstyle(String fstyle) {
		this.fstyle = fstyle;
	}
	public boolean isFshow() {
		return fshow;
	}
	public void setFshow(boolean fshow) {
		this.fshow = fshow;
	}
	public boolean isFrequired() {
		return frequired;
	}
	public void setFrequired(boolean frequired) {
		this.frequired = frequired;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}

}