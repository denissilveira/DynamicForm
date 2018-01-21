package com.poc.dynamicform.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="DYNAMICFIELD")
@DiscriminatorValue(value = "FIELD")
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class DynamicField extends DynamicElement {
	
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "DYNAMICTYPE", referencedColumnName = "ID")
	@NotNull
	private DynamicType dynamicType;
	@Column(name="NAME")
	private String name;
	@Column(name="LABEL")
	private String label;
	@Column(name="VALUE")
	private String value;
	@Column(name="STYLE")
	private String style;
	@Column(name="SHOW")
	private boolean show;
	@Column(name="REQUIRED")
	private boolean required;
	@Column(name="SHOWLABEL")
	private boolean showLabel;
	@Column(name="ACTION")
	private String action;
	@Column(name="ACTIONTYPE")
	private String actionType;
	@Transient
	private List<DynamicOption> options;
	
	public DynamicType getDynamicType() {
		return dynamicType;
	}
	public void setDynamicType(DynamicType dynamicType) {
		this.dynamicType = dynamicType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public List<DynamicOption> getOptions() {
		return options == null ? new ArrayList<DynamicOption>() : options;
	}
	public void setOptions(List<DynamicOption> options) {
		this.options = options;
	}
	
}