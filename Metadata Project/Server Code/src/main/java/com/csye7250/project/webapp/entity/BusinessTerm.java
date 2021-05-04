package com.csye7250.project.webapp.entity;
import com.csye7250.project.webapp.entity.Property;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "businessId")
@Entity
@Table(name="BusinessTerm")
public class BusinessTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BusinessId")
    private int businessId;
    private String BusinessDesc;
    @Column(name="Bustype")
    private String BusType;


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "DomBusBrdge",
            joinColumns = @JoinColumn(name = "BusinessId"),
            inverseJoinColumns = @JoinColumn(name = "DBName")
    )
    private List<Domain> domainList;

    @ManyToMany
    @JoinTable(
            name = "BusPropBridge",
            joinColumns = @JoinColumn(name = "BusinessId"),
            inverseJoinColumns = @JoinColumn(name = "PropertyId")
    )
    private List<Property> propertyList;

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusinessDesc() {
        return BusinessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        BusinessDesc = businessDesc;
    }

    public String getBusType() {
        return BusType;
    }

    public void setBusType(String busType) {
        BusType = busType;
    }

    public List<Domain> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<Domain> domainList) {
        this.domainList = domainList;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }
}
