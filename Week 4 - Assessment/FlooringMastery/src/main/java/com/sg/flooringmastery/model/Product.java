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
public class Product {
    private String productType;
    private BigDecimal costPerSqFt;
    private BigDecimal laborCostPerSqFt;

    public Product(String productType, BigDecimal costPerSqFt, BigDecimal laborCostPerSqFt) {
        this.productType = productType;
        this.costPerSqFt = costPerSqFt;
        this.laborCostPerSqFt = laborCostPerSqFt;
    }
    
    public String getProductType() {
        return productType;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }
    
    
}
