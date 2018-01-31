package com.poc.dynamicform.web.form;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Field extends Element{
	
	private Type type;
	private String label;
	private String value;
	private String style;
	private boolean required;
	private boolean showLabel;
	private boolean containsSubForm;
	private List<Option> options;
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public boolean isShowLabel() {
		return showLabel;
	}
	public void setShowLabel(boolean showLabel) {
		this.showLabel = showLabel;
	}
	public List<Option> getOptions() {
		return options == null ? new ArrayList<Option>() : options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
    public boolean isContainsSubForm() {
        return containsSubForm;
    }
    public void setContainsSubForm(boolean containsSubForm) {
        this.containsSubForm = containsSubForm;
    }

}