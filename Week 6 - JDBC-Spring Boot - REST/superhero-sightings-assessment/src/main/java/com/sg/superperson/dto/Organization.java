/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superperson.dto;

import java.util.Objects;

/**
 *
 * @author Cosmos
 */
public class Organization {
    private int organizationId;
    private String organizationName;
    private String organizationDesc;
    private String organizationCont;

    public Organization(int organizationId, String organizationName, String organizationDesc, String organizationCont) {
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.organizationDesc = organizationDesc;
        this.organizationCont = organizationCont;
    }

    public Organization() {
    }
    
    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDesc() {
        return organizationDesc;
    }

    public void setOrganizationDesc(String organizationDesc) {
        this.organizationDesc = organizationDesc;
    }

    public String getOrganizationAddr() {
        return organizationCont;
    }

    public void setOrganizationAddr(String organizationCont) {
        this.organizationCont = organizationCont;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.organizationId;
        hash = 29 * hash + Objects.hashCode(this.organizationName);
        hash = 29 * hash + Objects.hashCode(this.organizationDesc);
        hash = 29 * hash + Objects.hashCode(this.organizationCont);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.organizationName, other.organizationName)) {
            return false;
        }
        if (!Objects.equals(this.organizationDesc, other.organizationDesc)) {
            return false;
        }
        return Objects.equals(this.organizationCont, other.organizationCont);
    }
    
}
