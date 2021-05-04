package com.csye7250.project.webapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "nodeId")
@Entity
@Table(name = "Nodes")
public class Node {
    @Id
    @Column(name="NodeId")
    private int nodeId;
    @Column(name="Label")
    private String label;
    @Column(name="Counts")
    private int counts;


    @Column(name="DBName")
    private String dbName;


    @OneToMany
	@JoinColumn(name = "PropertyId")
    private List<Property> props;
    
    @OneToMany
	@JoinColumn(name = "RelationId")
    private List<Relationship> relList;

    public List<Property> getProps() {
		return props;
	}

	public void setProps(List<Property> props) {
		this.props = props;
	}

	public List<Relationship> getRelList() {
		return relList;
	}

	public void setRelList(List<Relationship> relList) {
		this.relList = relList;
	}

	public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}

