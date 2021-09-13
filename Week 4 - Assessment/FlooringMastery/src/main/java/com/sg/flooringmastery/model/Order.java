/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Paul
 */
public class Order {
    private int orderNumber;
    private LocalDate orderDate;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSqFt;
    private BigDecimal laborCostPerSqFt;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal totalTax;
    private BigDecimal orderTotal;

    // Add order contructor
    public Order(String customerName, String state, String productType, LocalDate orderDate,
            BigDecimal area, BigDecimal taxRate, BigDecimal costPerSqFt, BigDecimal laborCostPerSqFt) {
        this.customerName = customerName;
        this.state = state;
        this.productType = productType;
        this.orderDate = orderDate;
        this.area = area;
        this.taxRate = taxRate;
        this.costPerSqFt = costPerSqFt;
        this.laborCostPerSqFt = laborCostPerSqFt;
        calculateOrder();
        bdScale();
    }
    
    // Read from file constructor
    public Order(String orderNumber, String customerName, String state, BigDecimal taxRate, 
            String productType, BigDecimal area, BigDecimal costPerSqFt, BigDecimal laborCostPerSqFt, 
            BigDecimal materialCost, BigDecimal laborCost, BigDecimal totalTax, 
            BigDecimal orderTotal) {
        this.orderNumber = Integer.parseInt(orderNumber);
        this.customerName = customerName;
        this.state = state;
        this.productType = productType;
        this.taxRate = taxRate;
        this.costPerSqFt = costPerSqFt;
        this.laborCostPerSqFt = laborCostPerSqFt;
        this.area = area;
        this.materialCost = materialCost;
        this.laborCost = laborCost;
        this.totalTax = totalTax;
        this.orderTotal = orderTotal;
        bdScale();
    }

    public Order() {
    }
    
    private void calculateOrder() {
        this.materialCost = area.multiply(costPerSqFt);
        this.laborCost = area.multiply(laborCostPerSqFt);
        this.totalTax = (materialCost.add(laborCost).multiply(taxRate.divide(new BigDecimal("100"))));
        this.orderTotal = materialCost.add(laborCost).add(totalTax);
    }
    
    private void bdScale() {
        taxRate = taxRate.setScale(3, RoundingMode.HALF_UP);
        costPerSqFt = costPerSqFt.setScale(3, RoundingMode.HALF_UP);
        laborCostPerSqFt = laborCostPerSqFt.setScale(3, RoundingMode.HALF_UP);
        area = area.setScale(2, RoundingMode.HALF_UP);
        materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);
        laborCost = laborCost.setScale(2, RoundingMode.HALF_UP);
        totalTax = totalTax.setScale(2, RoundingMode.HALF_UP);
        orderTotal = orderTotal.setScale(2, RoundingMode.HALF_UP);
    }
    
    public void recalculate() {
        calculateOrder();
        bdScale();
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.orderNumber;
        hash = 13 * hash + Objects.hashCode(this.customerName);
        hash = 13 * hash + Objects.hashCode(this.state);
        hash = 13 * hash + Objects.hashCode(this.taxRate);
        hash = 13 * hash + Objects.hashCode(this.productType);
        hash = 13 * hash + Objects.hashCode(this.area);
        hash = 13 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 13 * hash + Objects.hashCode(this.laborCostPerSqFt);
        hash = 13 * hash + Objects.hashCode(this.materialCost);
        hash = 13 * hash + Objects.hashCode(this.laborCost);
        hash = 13 * hash + Objects.hashCode(this.totalTax);
        hash = 13 * hash + Objects.hashCode(this.orderTotal);
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
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSqFt, other.laborCostPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalTax, other.totalTax)) {
            return false;
        }
        if (!Objects.equals(this.orderTotal, other.orderTotal)) {
            return false;
        }
        return true;
    }
}
