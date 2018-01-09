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
public class DynanicOption implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="seq_dynamicoption_id",sequenceName = "seq_dynamicoption_id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_dynamicoption_id")
    @Basic(optional = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "dynamicField", referencedColumnName = "ID")
	@NotNull
	private DynamicField dynamicField;
	private String doption;
	private String dvalue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DynamicField getDynamicField() {
		return dynamicField;
	}
	public void setDynamicField(DynamicField dynamicField) {
		this.dynamicField = dynamicField;
	}
	public String getDoption() {
		return doption;
	}
	public void setDoption(String doption) {
		this.doption = doption;
	}
	public String getDvalue() {
		return dvalue;
	}
	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}

}