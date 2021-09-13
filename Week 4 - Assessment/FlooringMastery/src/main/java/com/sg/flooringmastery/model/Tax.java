/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.model;

import java.math.BigDecimal;

/**
 *
 * @author Paul
 */
public class Tax {
    private String stateAbbr;
    private String stateName;
    private BigDecimal taxRate;

    public Tax(String stateAbbr, String stateName, BigDecimal taxRate) {
        this.stateAbbr = stateAbbr;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }
    
    public String getStateAbbr() {
        return stateAbbr;
    }

    public String getStateName() {
        return stateName;
    }

    public BigDecimal getStateTaxRate() {
        return taxRate;
    }
    
    
}
