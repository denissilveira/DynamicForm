package com.poc.dynamicform.web.form;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Form {
	
	private Long id;
	private String name;
	private Integer version;
	List<Group> groups;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public List<Group> getGroups() {
		return groups == null ? new ArrayList<Group>() : groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}