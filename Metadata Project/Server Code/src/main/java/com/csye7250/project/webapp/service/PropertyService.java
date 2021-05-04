package com.csye7250.project.webapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csye7250.project.webapp.entity.Property;
import com.csye7250.project.webapp.exception.PropertyException;
import com.csye7250.project.webapp.repository.PropertyRepository;
import com.csye7250.project.webapp.util.CustomStrings;

@Service
public class PropertyService {
	private PropertyRepository propertyRepo;

    @Autowired
    public PropertyService( PropertyRepository propertyRepository){
        this.propertyRepo=propertyRepository;
    }


    public List<Property> getAllProperties(int node) throws PropertyException {
        List<Property> property = new ArrayList<>();
        try{
           this.propertyRepo.findByNodeId(node).forEach(property::add);
           return property;
        }catch (Exception e){
            throw new PropertyException(e.getMessage());
        }
    }
    
    public Property getPropertyById(int id) throws PropertyException {
        try{
        	Optional<Property> opProp = this.propertyRepo.findById(id);
        	if(opProp.isPresent())
        		return opProp.get();
        	else
        		throw new PropertyException(CustomStrings.notFound);
        }catch (Exception e){
            throw new PropertyException(e.getMessage());
        }
    }

    public List<Property>  fetchPropertyForDB(String dbName) throws PropertyException {
        List<Property> propertyList = new ArrayList<>();
        try {
            this.propertyRepo.customFindProperty(dbName).forEach(p ->{
                    Property prop = new Property();
                    prop.setPropertyId((int)p[0]);
                    prop.setTechTerm((String)p[1]);
                    prop.setUniqueConstraints((String)p[2]);
                    prop.setNodeId((int)p[3]);
                    prop.setExistingConstraints((String)p[4]);
                propertyList.add(prop);
            });
            return propertyList;
        }catch (Exception e){
            throw new PropertyException(e.getMessage());
        }
    }
}
