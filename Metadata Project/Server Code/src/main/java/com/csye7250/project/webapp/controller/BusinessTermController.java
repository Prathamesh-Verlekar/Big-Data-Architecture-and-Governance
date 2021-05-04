package com.csye7250.project.webapp.controller;

import com.csye7250.project.webapp.entity.BusinessTerm;
import com.csye7250.project.webapp.exception.BusinessTermException;
import com.csye7250.project.webapp.service.BusinessTermService;
import com.csye7250.project.webapp.util.CustomStrings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class BusinessTermController {

    private BusinessTermService businessTermService;

    @Autowired
    public BusinessTermController(BusinessTermService bTermService){
        this.businessTermService=bTermService;
    }

    @GetMapping("/businessterms")
    public ResponseEntity<List<BusinessTerm>> getAllBusinessTerms() throws BusinessTermException {

        List<BusinessTerm> bussTerms = this.businessTermService.getAllbusinessTerms();

        if(null!=bussTerms && bussTerms.size()>0)
            return new ResponseEntity(bussTerms, HttpStatus.valueOf(200));
        else
            throw new BusinessTermException(CustomStrings.notFound);
    }

    @PostMapping("/businessterms")
    public ResponseEntity<BusinessTerm> saveBusinessTerms(@RequestBody @NotNull BusinessTerm bterm) throws BusinessTermException {

        System.out.println(bterm.getBusinessDesc());
        BusinessTerm saved =  this.businessTermService.savebusinessTerm(bterm);
        System.out.println(saved.getBusinessDesc());
        if(null!=saved )
        return new ResponseEntity(saved, HttpStatus.valueOf(200));
        else
        throw new BusinessTermException(CustomStrings.notsaved);

    }


}
