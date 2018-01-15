package com.poc.dynamicform.web.form;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Group {
	
	private String name;
	private String type;
	private boolean show;
	private boolean showName;
	private List<Group> groups;
	private List<Field> fields;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public boolean isShowName() {
		return showName;
	}
	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	public List<Group> getGroups() {
		return groups == null ? new ArrayList<Group>() : groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
}