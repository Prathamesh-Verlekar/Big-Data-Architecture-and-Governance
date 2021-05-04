package com.csye7250.project.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.csye7250.project.webapp.entity.Property;

public interface PropertyRepository extends CrudRepository<Property, Integer> {
	List<Property> findByNodeId(Integer nodeId);

//	new PropertyDTO(p.propertyId,p.techTerm,p.uniqueConstraints,p.nodeId,p.existingConstraints)
	@Query("SELECT p.propertyId,p.techTerm,p.uniqueConstraints,p.nodeId,p.existingConstraints FROM Property p JOIN Node n on n.nodeId = p.nodeId JOIN Domain d on d.dbName =n.dbName where d.dbName =:dbName")
	List<Object[]> customFindProperty(@Param("dbName") String dbName);
}
