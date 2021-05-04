package com.csye7250.project.webapp.entity;

import javax.persistence.*;

@Entity
public class Relationship {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RelationId")
	private int relationshipId;

	@Column(name="RelDesc")
	private String relDesc;

	@Column(name="NodeId")
	private int nodeId;

	public int getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(int relationshipId) {
		this.relationshipId = relationshipId;
	}

	public String getRelDesc() {
		return relDesc;
	}

	public void setRelDesc(String relDesc) {
		this.relDesc = relDesc;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
}
