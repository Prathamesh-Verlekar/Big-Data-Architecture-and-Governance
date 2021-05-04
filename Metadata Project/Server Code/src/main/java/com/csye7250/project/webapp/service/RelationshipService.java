package com.csye7250.project.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csye7250.project.webapp.entity.Relationship;
import com.csye7250.project.webapp.exception.RelationshipException;
import com.csye7250.project.webapp.repository.RelationshipRepository;

@Service
public class RelationshipService {
	private RelationshipRepository relationshipRepo;

    @Autowired
    public RelationshipService(RelationshipRepository propertyRepository){
        this.relationshipRepo=propertyRepository;
    }


    public List<Relationship> getAllRelationshipByNode(int node) throws RelationshipException {
        List<Relationship> relationship = new ArrayList<>();
        try{
           this.relationshipRepo.findByNodeId(node).forEach(relationship::add);
           return relationship;
        }catch (Exception e){
            throw new RelationshipException(e.getMessage());
        }
    }
}
