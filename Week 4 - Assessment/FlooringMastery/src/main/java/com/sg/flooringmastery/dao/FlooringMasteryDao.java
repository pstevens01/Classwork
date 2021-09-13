/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import java.io.IOError;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Paul
 */
public interface FlooringMasteryDao {
    
    /**
     * Adds order to the map object from user input
     * @param order
     * @return 
     */
    public int addOrder(Order order);
    
    /**
     * 
     * @param date
     * @param customerName
     * @param state
     * @param productType
     * @param area
     * @return 
     */
    public Order createOrder(String date, String customerName, String state, String productType, BigDecimal area);
    
    /**
     * Returns a list of order objects matching the specified date
     * @param date
     * @return 
     */
    public List<Order> getOrdersByDate(LocalDate date);
    
    /**
     * Returns an order if it matches the order number and date
     * @param orderNum
     * @param date
     * @return 
     */
    public Order getOrderByNumberAndDate(String orderNum, String date);
    
    /**
     * 
     * @param date
     * @param customerName
     * @return 
     */
    public Order getOrderByNameDate(String date, String customerName);
    
    /**
     * Returns a list of all orders
     * @return 
     */
    public List<Order> getAllOrders();
    
    /**
     * Edits an existing order by replacing the old values with new values
     * (if applicable)
     * @param order 
     */
    public void editOrder(Order order) throws FlooringMasteryPersistenceException;
    
    /**
     * Removes order map object
     * @param order 
     */
    public void removeOrder(Order order);
    
    /**
     * 
     * @param order
     * @throws FlooringMasteryPersistenceException 
     */
    public void updateOrder(Order order) throws FlooringMasteryPersistenceException;
    
    /**
     * 
     * @param order 
     */
    public void recalculateOrder(Order order);
    
    /**
     * 
     * @return 
     */
    public List<Product> getProducts();
    
    /**
     * 
     * @throws FlooringMasteryPersistenceException 
     */
    public void saveOrderData() throws FlooringMasteryPersistenceException;
    
    /**
     * 
     * @throws FlooringMasteryPersistenceException 
     */
    public void loadOrderData() throws FlooringMasteryPersistenceException;
    
    /**
     * 
     * @throws FlooringMasteryPersistenceException 
     */
    public void exportOrdersForBackup() throws FlooringMasteryPersistenceException;
    
    /**
     * 
     * @throws FlooringMasteryPersistenceException 
     */
    public void loadProductData() throws FlooringMasteryPersistenceException;
    
    /**
     * 
     * @throws FlooringMasteryPersistenceException 
     */
    public void loadTaxData() throws FlooringMasteryPersistenceException;
    
    public boolean hasTaxCode(String abbv);
    
    public boolean hasProductType(String product);
}
