package com.poc.dynamicform.domain.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="DYNAMICFIELD")
public class DynamicField implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="seq_dynamicfield_id",sequenceName = "seq_dynamicfield_id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_dynamicfield_id")
    @Basic(optional = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "DYNAMICGROUP", referencedColumnName = "ID")
	@NotNull
	private DynamicGroup dynamicGroup;
	@ManyToOne
	@JoinColumn(name = "DYNAMICTYPE", referencedColumnName = "ID")
	@NotNull
	private DynamicType dynamicType;
	private String name;
	private String label;
	private String value;
	private String style;
	private boolean show;
	private boolean required;
	private boolean showLabel;
	private String action;
	private String actionType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DynamicGroup getDynamicGroup() {
		return dynamicGroup;
	}
	public void setDynamicGroup(DynamicGroup dynamicGroup) {
		this.dynamicGroup = dynamicGroup;
	}
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
	
}