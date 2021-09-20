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
public class Superperson {
    private int superpersonId;
    private String superpersonName;
    private String superpersonDesc;
    private int superpersonPowerId;

    public Superperson(int superpersonId, String superpersonName, String superpersonDesc, int superpersonPowerId, int organizationId) {
        this.superpersonId = superpersonId;
        this.superpersonName = superpersonName;
        this.superpersonDesc = superpersonDesc;
        this.superpersonPowerId = superpersonPowerId;
    }

    public Superperson(String superpersonName, String superpersonDesc, int superpersonPowerId) {
        this.superpersonName = superpersonName;
        this.superpersonDesc = superpersonDesc;
        this.superpersonPowerId = superpersonPowerId;
    }

    public Superperson(int superpersonId) {
        this.superpersonId = superpersonId;
    }
    
    public Superperson() {
    }    

    public int getSuperpersonId() {
        return superpersonId;
    }

    public void setSuperpersonId(int superpersonId) {
        this.superpersonId = superpersonId;
    }

    public String getSuperpersonName() {
        return superpersonName;
    }

    public void setSuperpersonName(String superpersonName) {
        this.superpersonName = superpersonName;
    }

    public String getSuperpersonDesc() {
        return superpersonDesc;
    }

    public void setSuperpersonDesc(String superpersonDesc) {
        this.superpersonDesc = superpersonDesc;
    }

    public int getSuperpersonPowerId() {
        return superpersonPowerId;
    }

    public void setSuperpersonPowerId(int superpersonPowerId) {
        this.superpersonPowerId = superpersonPowerId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.superpersonId;
        hash = 47 * hash + Objects.hashCode(this.superpersonName);
        hash = 47 * hash + Objects.hashCode(this.superpersonDesc);
        hash = 47 * hash + Objects.hashCode(this.superpersonPowerId);
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
        final Superperson other = (Superperson) obj;
        if (this.superpersonId != other.superpersonId) {
            return false;
        }
        if (!Objects.equals(this.superpersonName, other.superpersonName)) {
            return false;
        }
        if (!Objects.equals(this.superpersonDesc, other.superpersonDesc)) {
            return false;
        }
        return Objects.equals(this.superpersonPowerId, other.superpersonPowerId);
    }
        
}
