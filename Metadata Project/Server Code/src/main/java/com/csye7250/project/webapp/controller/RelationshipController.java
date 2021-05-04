package com.csye7250.project.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csye7250.project.webapp.entity.Relationship;
import com.csye7250.project.webapp.exception.RelationshipException;
import com.csye7250.project.webapp.service.RelationshipService;
import com.csye7250.project.webapp.util.CustomStrings;

@RestController
public class RelationshipController {

	private RelationshipService relService;
	
	@Autowired
	public RelationshipController(RelationshipService relService) {
		this.relService = relService;
	}
	
	@GetMapping("/relationships")
    public ResponseEntity<List<Relationship>> getAllRelationshipByNode(@RequestParam("node") int node) throws RelationshipException {

        List<Relationship> relationshipList = this.relService.getAllRelationshipByNode(node);

        if(null!=relationshipList && relationshipList.size()>0)
            return new ResponseEntity<List<Relationship>>(relationshipList, HttpStatus.valueOf(200));
        else
            throw new RelationshipException(CustomStrings.notFound);
    }
}
