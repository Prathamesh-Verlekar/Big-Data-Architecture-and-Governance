package com.csye7250.project.webapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "propertyId")
@Entity
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PropertyId")
	private int propertyId;
	@Column(name="TechTerm")
	private String techTerm;
	@Column(name="UniqueConstraints")
	private String uniqueConstraints;
	@Column(name="NodeId")
	private int nodeId;
	@Column(name="ExistingConstraints")
	private String existingConstraints;

	@ManyToMany(cascade=CascadeType.ALL, mappedBy="propertyList")
	private List<BusinessTerm> bTermList ;

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getTechTerm() {
		return techTerm;
	}

	public void setTechTerm(String techTerm) {
		this.techTerm = techTerm;
	}

	public String getUniqueConstraints() {
		return uniqueConstraints;
	}

	public void setUniqueConstraints(String uniqueConstraints) {
		this.uniqueConstraints = uniqueConstraints;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getExistingConstraints() {
		return existingConstraints;
	}

	public void setExistingConstraints(String existingConstraints) {
		this.existingConstraints = existingConstraints;
	}

	public List<BusinessTerm> getbTermList() {
		return bTermList;
	}

	public void setbTermList(List<BusinessTerm> bTermList) {
		this.bTermList = bTermList;
	}
}
