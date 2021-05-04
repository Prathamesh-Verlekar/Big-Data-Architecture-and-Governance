package com.csye7250.project.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csye7250.project.webapp.entity.Property;
import com.csye7250.project.webapp.exception.PropertyException;
import com.csye7250.project.webapp.service.PropertyService;
import com.csye7250.project.webapp.util.CustomStrings;

@RestController
public class PropertyController {
	
	private PropertyService propertyService;
	
	@Autowired
	public PropertyController(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	@GetMapping("/properties")
    public ResponseEntity<List<Property>> getAllProperties(@RequestParam("node") int node) throws PropertyException {

        List<Property> propertyList = this.propertyService.getAllProperties(node);

        if(null!=propertyList && propertyList.size()>0)
            return new ResponseEntity<List<Property>>(propertyList, HttpStatus.valueOf(200));
        else
            throw new PropertyException(CustomStrings.notFound);
    }
	
	@GetMapping("/properties/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable int id) throws PropertyException {

        Property property = this.propertyService.getPropertyById(id);
        return new ResponseEntity<Property>(property, HttpStatus.valueOf(200));
        
    }

    @GetMapping("/properties/dbName/{dbName}")
    public ResponseEntity<List<Property> > fetchPropertyForDB(@PathVariable String dbName) throws PropertyException {

        List<Property>  property = this.propertyService.fetchPropertyForDB(dbName);
        return new ResponseEntity<List<Property> >(property, HttpStatus.valueOf(200));

    }
}
