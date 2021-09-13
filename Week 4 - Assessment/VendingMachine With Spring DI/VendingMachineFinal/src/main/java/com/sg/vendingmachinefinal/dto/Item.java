/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinefinal.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Paul
 */
public class Item {
    private String name;
    private BigDecimal cost;
    private int invCount;

    
    public Item(String name, BigDecimal cost, int inventoryCount) {
        this.name = name;
        this.cost = cost;
        this.invCount = inventoryCount;
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getInvCount() {
        return invCount;
    }

    public void setInvCount(int invCount) {
        this.invCount = invCount;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.cost);
        hash = 79 * hash + this.invCount;
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
        final Item other = (Item) obj;
        if (this.invCount != other.invCount) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", cost=" + cost + ", invCount=" + invCount + '}';
    }
}
