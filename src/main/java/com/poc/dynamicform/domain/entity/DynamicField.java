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
import javax.validation.constraints.NotNull;

@Entity
public class DynamicField implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="seq_dynamicfield_id",sequenceName = "seq_dynamicfield_id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_dynamicfield_id")
    @Basic(optional = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "dynamicGroup", referencedColumnName = "ID")
	@NotNull
	private DynamicGroup dynamicGroup;
	@ManyToOne
	@JoinColumn(name = "dynamicType", referencedColumnName = "ID")
	@NotNull
	private DynamicType dynamicType;
	private String fname;
	private String flabel;
	private String fvalue;
	private String fstyle;
	private boolean fshow;
	private boolean frequired;
	
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
	
}