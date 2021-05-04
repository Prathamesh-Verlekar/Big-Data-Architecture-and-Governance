package com.csye7250.project.webapp.controller;

import com.csye7250.project.webapp.entity.Domain;
import com.csye7250.project.webapp.entity.Node;
import com.csye7250.project.webapp.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @GetMapping("/getAllNodes")
    public List<Node> getAll(){
        return nodeService.getAllNodes();
    }

    @GetMapping("/findById/{id}")
    public Node findNodeById(@PathVariable int id) {
        return nodeService.findNodeById(id);
    }


    @GetMapping("/findByDomain/{domain}")
    public Node findNodeByDomain(@PathVariable String domainName) {
        return nodeService.findNodeByDomain(domainName);
    }
}
