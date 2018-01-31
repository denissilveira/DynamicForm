package com.poc.dynamicform.web.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Option {
	
	private String option;
	private String value;
	private String selectedValue;
	
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
    public String getSelectedValue() {
        return selectedValue;
    }
    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }
	
	

}