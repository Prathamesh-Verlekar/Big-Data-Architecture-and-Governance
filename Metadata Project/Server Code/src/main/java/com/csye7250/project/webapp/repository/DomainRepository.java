package com.csye7250.project.webapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.csye7250.project.webapp.entity.Domain;

import java.util.List;

public interface DomainRepository extends CrudRepository<Domain, String> {

    //List<Domain> findByDBName(String dbName);

}
