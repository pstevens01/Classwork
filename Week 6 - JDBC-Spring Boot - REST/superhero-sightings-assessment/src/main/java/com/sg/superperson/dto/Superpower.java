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
public class Superpower {
    private int superpowerId;
    private String superpowerName;

    public Superpower() {
    }

    public Superpower(int superpowerId, String superpowerName) {
        this.superpowerId = superpowerId;
        this.superpowerName = superpowerName;
    }

    public Superpower(String superpowerName) {
        this.superpowerName = superpowerName;
    }

    public int getSuperpowerId() {
        return superpowerId;
    }

    public void setSuperpowerId(int superpowerId) {
        this.superpowerId = superpowerId;
    }

    public String getSuperpowerName() {
        return superpowerName;
    }

    public void setSuperpowerName(String superpowerName) {
        this.superpowerName = superpowerName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.superpowerId;
        hash = 89 * hash + Objects.hashCode(this.superpowerName);
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
        final Superpower other = (Superpower) obj;
        return Objects.equals(this.superpowerName, other.superpowerName);
    }    
}
