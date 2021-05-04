package com.csye7250.project.webapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.csye7250.project.webapp.entity.Relationship;

public interface RelationshipRepository extends CrudRepository<Relationship, Integer> {
	List<Relationship> findByNodeId(Integer nodeId);
}
