package com.poc.dynamicform.domain.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name="DYNAMICOPTION")
public class DynamicOption implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="seq_dynamicoption_id",sequenceName = "seq_dynamicoption_id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_dynamicoption_id")
    @Basic(optional = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "DYNAMICFIELD", referencedColumnName = "ID")
	@NotNull
	private DynamicField dynamicField;
	@Column(name="OPTION")
	private String option;
	@Column(name="VALUE")
	private String value;
	
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

}