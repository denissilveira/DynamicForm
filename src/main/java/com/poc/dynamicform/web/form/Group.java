package com.poc.dynamicform.web.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Group extends Element {
	
	private String name;
	private String type;
	private boolean show;
	private boolean showName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}