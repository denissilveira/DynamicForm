package com.poc.dynamicform.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="DYNAMICFORM")
public class DynamicForm implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="seq_dynamicform_id",sequenceName = "seq_dynamicform_id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_dynamicform_id")
    @Basic(optional = false)
	private Long id;
	@Column(name="NAME")
	private String name;
	@Column(name="VERSION")
	private Integer version;
	@Column(name="METHOD")
	private String method;
	@Column(name="ACTION")
	private String action;
	@Column(name="SHOW")
	private boolean show;
	@Transient
	private List<DynamicGroup> groups;
	
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
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public List<DynamicGroup> getGroups() {
		return groups;
	}
	public void setGroups(List<DynamicGroup> groups) {
		this.groups = groups == null ? new ArrayList<DynamicGroup>() : groups;
	}
	
}