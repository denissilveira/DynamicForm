package com.poc.dynamicform.domain.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DYNAMICFORM")
public class DynamicForm implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="seq_dynamicform_id",sequenceName = "seq_dynamicform_id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_dynamicform_id")
    @Basic(optional = false)
	private Long id;
	private String name;
	private Integer version;
	private String method;
	private String action;
	private boolean show;
	
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
	
}