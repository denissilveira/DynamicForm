package com.poc.dynamicform.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(columnDefinition = "TYPE", name = "TYPE")                                                                                                                                   
@Table(name="DYNAMICELEMENT")
public class DynamicElement implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq_dynamicelement_id",sequenceName = "seq_dynamicelement_id", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_dynamicelement_id")
    @Basic(optional = false)
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "DYNAMICFORM", referencedColumnName = "ID")
    @NotNull
    protected DynamicForm dynamicForm;
    @Column(name="POSITION")
    @NotNull
    protected Integer position;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT", referencedColumnName = "ID", updatable = false, insertable = false)
    private DynamicElement parent;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<DynamicElement> elements;
    
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
    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }
    public DynamicElement getParent() {
        return parent;
    }
    public void setParent(DynamicElement parent) {
        this.parent = parent;
    }
    public List<DynamicElement> getElements() {
        return elements == null ? new ArrayList<DynamicElement>() : elements;
    }
    public void setElements(List<DynamicElement> elements) {
        this.elements = elements;
    }
    
}