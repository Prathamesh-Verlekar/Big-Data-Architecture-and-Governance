package com.csye7250.project.webapp.service;

import com.csye7250.project.webapp.entity.Node;
import com.csye7250.project.webapp.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    public List<Node> getAllNodes() {
        List<Node> list = new ArrayList<>();
        nodeRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public Node findNodeById(int id) {
        return nodeRepository.findById(id).get();
    }

    public Node findNodeByDomain(String domainName) {
        return nodeRepository.findByDbName(domainName);
    }
}
