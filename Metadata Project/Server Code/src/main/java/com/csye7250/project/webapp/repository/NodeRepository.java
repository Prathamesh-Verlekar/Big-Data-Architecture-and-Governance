package com.csye7250.project.webapp.repository;

import com.csye7250.project.webapp.entity.Domain;
import org.springframework.data.repository.CrudRepository;

import com.csye7250.project.webapp.entity.Node;

import java.util.List;

public interface NodeRepository extends CrudRepository<Node, Integer> {
    Node findByDbName(String domainName);
}
