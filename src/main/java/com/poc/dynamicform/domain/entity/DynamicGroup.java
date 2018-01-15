package com.poc.dynamicform.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="DYNAMICGROUP")
public class DynamicGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="seq_dynamicgroup_id",sequenceName = "seq_dynamicgroup_id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_dynamicgroup_id")
    @Basic(optional = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "DINAMICFORM", referencedColumnName = "ID")
	@NotNull
	private DynamicForm dynamicForm;
	@Column(name="NAME")
	private String name;
	@Column(name="TYPE")
	private String type;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT", referencedColumnName = "ID", updatable = false, insertable = false)
	private DynamicGroup parent;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private List<DynamicGroup> groups;
	@Column(name="SHOW")
	private boolean show;
	@Column(name="SHOWNAME")
	private boolean showName;
	@Transient
	private List<DynamicField> fields;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DynamicForm getDynamicForm() {
		return dynamicForm;
	}
	public void setDynamicForm(DynamicForm dynamicForm) {
		this.dynamicForm = dynamicForm;
	}
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
	public DynamicGroup getParent() {
		return parent;
	}
	public void setParent(DynamicGroup parent) {
		this.parent = parent;
	}
	public List<DynamicGroup> getGroups() {
		return groups == null ? new ArrayList<DynamicGroup>() : groups;
	}
	public void setGroups(List<DynamicGroup> groups) {
		this.groups = groups;
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
	public List<DynamicField> getFields() {
		return fields == null ? new ArrayList<DynamicField>() : fields;
	}
	public void setFields(List<DynamicField> fields) {
		this.fields = fields;
	}
	
}