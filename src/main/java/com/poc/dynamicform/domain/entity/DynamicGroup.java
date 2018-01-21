package com.poc.dynamicform.domain.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="DYNAMICGROUP")
@DiscriminatorValue(value = "GROUP")
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="ID")
public class DynamicGroup extends DynamicElement {
	
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "DINAMICFORM", referencedColumnName = "ID")
	@NotNull
	private DynamicForm dynamicForm;
	@Column(name="NAME")
	private String name;
	@Column(name="TYPE")
	private String type;
	@Column(name="SHOW")
	private boolean show;
	@Column(name="SHOWNAME")
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