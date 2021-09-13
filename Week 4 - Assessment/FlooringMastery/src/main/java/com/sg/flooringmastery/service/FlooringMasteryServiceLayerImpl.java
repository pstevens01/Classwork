/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul
 */
@Component
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    private final FlooringMasteryDao dao;
    private final FlooringMasteryAuditDao auditDao;
    
    @Autowired
    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao, FlooringMasteryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    @Override
    public List<Order> getOrdersByDate(String date) throws FlooringMasteryInvalidInputException, FlooringMasteryPersistenceException {
        return dao.getOrdersByDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy")));
    }

    @Override
    public Order createOrder(String date, String customerName, String state, String productType, String area) throws FlooringMasteryInvalidInputException, FlooringMasteryPersistenceException {
        BigDecimal bdArea = new BigDecimal(area);
        
        // Verify valid date
        if(LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy")).isBefore(LocalDate.now())){
            throw new FlooringMasteryInvalidInputException("The date entered is before today's date.");
        }
        // Verify state exists in tax file
        else if(!dao.hasTaxCode(state)){
            throw new FlooringMasteryInvalidInputException("State entered is not present in the tax code file.");
        }
        // Verify the productType is a valid product 
        else if(!dao.hasProductType(productType)){
            throw new FlooringMasteryInvalidInputException("The product type entered does not exist.");
        }
        
        return dao.createOrder(date, customerName, state, productType, bdArea);
    }

    @Override
    public int submitOrder(Order order) throws FlooringMasteryPersistenceException {
        auditDao.writeAuditEntry(order.getCustomerName() + "'s order submitted for " + order.getOrderDate()); 
        return dao.addOrder(order);
    }

    @Override
    public List<Product> getProducts() throws FlooringMasteryPersistenceException {
        return dao.getProducts();
    }

    @Override
    public void importAllData() throws FlooringMasteryPersistenceException {
        try {
            dao.loadOrderData();
            dao.loadProductData();
            dao.loadTaxData();
        } catch (FlooringMasteryPersistenceException e) {
            throw new FlooringMasteryPersistenceException(e.getMessage());
        }
    }

    @Override
    public Order editOrder(Order order, String customerName, String state, String productType, String area) throws FlooringMasteryInvalidInputException, FlooringMasteryPersistenceException {
        BigDecimal bdArea;
        if (area.equals("")) {
            bdArea = order.getArea();
        } else {
            bdArea = new BigDecimal(area);
        }
        
        //Verify state exists in tax file
        if(!state.equals("") && !dao.hasTaxCode(state)){
            throw new FlooringMasteryInvalidInputException("We cannot sell products in your state.");
        }
        //Verify the productType is a valid product 
        else if(!productType.equals("") && !dao.hasProductType(productType)){
            throw new FlooringMasteryInvalidInputException("The product type entered does not exist.");
        }
        
        if (!customerName.equals("")) {
            order.setCustomerName(customerName);
        }
        if (!area.equals("")) {
            order.setArea(bdArea);
        }
        if (!state.equals("")) {
            order.setState(state);
        }
        if (!productType.equals("")) {
            order.setProductType(productType);
        }
        
        auditDao.writeAuditEntry(order.getCustomerName() + "'s order has been edited. New values and costs have been updated.");
        dao.recalculateOrder(order);
        return order;
    }

    @Override
    public void changeOrder(Order order) throws FlooringMasteryPersistenceException {
        dao.updateOrder(order);
    }

    @Override
    public Order getOrder(String date, String orderNumber) throws FlooringMasteryInvalidInputException {
        Order order = dao.getOrderByNumberAndDate(orderNumber, date);
        
        if (order == null) {
            throw new FlooringMasteryInvalidInputException("There is no such order");
        } else {
            return order;
        }
    }

    @Override
    public Order getRemoveOrder(String date, String orderNumber) throws FlooringMasteryInvalidInputException, FlooringMasteryPersistenceException {
        Order order = dao.getOrderByNumberAndDate(orderNumber, date);
        
        if (order == null) {
            throw new FlooringMasteryInvalidInputException("There is no such order");
        } else {
            return order;
        }
    }
    
    @Override
    public void removeOrder(Order order) throws FlooringMasteryPersistenceException {
        auditDao.writeAuditEntry(order.getCustomerName() + "'s has been removed for date " + order.getOrderDate());
        dao.removeOrder(order);
    }

    @Override
    public void exportAllData() throws FlooringMasteryPersistenceException {
        dao.saveOrderData();
    }

    @Override
    public void exportBackupData() throws FlooringMasteryPersistenceException {
        auditDao.writeAuditEntry("Orders Exported to Backup.");
        dao.exportOrdersForBackup();
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        return dao.getAllOrders().stream()
                .collect(Collectors.toList());
    }
    
}
