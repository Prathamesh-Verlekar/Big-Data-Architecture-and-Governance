package com.csye7250.project.webapp.entity;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToMany;

@Entity
public class Domain {

    @Id
    @Column(name="DBName")
    private String dbName;
    @Column(name="DomainName")
    private String domainName;

    @OneToMany
    @JoinColumn(name = "nodeId")
    private List<Node> nodeList;



    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
