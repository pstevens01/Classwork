/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import java.util.List;

/**
 *
 * @author Paul
 */
public interface FlooringMasteryServiceLayer {

    public List<Order> getOrdersByDate(String date) throws FlooringMasteryInvalidInputException, FlooringMasteryPersistenceException;

    public Order createOrder(String date, String customerName, String state, String productType, String area)  throws 
            FlooringMasteryInvalidInputException, FlooringMasteryPersistenceException;
    
    public int submitOrder(Order order) throws FlooringMasteryPersistenceException;

    public List<Product> getProducts() throws FlooringMasteryPersistenceException;

    public void importAllData() throws FlooringMasteryPersistenceException;

    public Order getOrder(String date, String customerName) throws 
            FlooringMasteryInvalidInputException, FlooringMasteryPersistenceException;

    public Order editOrder(Order order, String customerName, String state, String productType, String area) throws 
            FlooringMasteryInvalidInputException, FlooringMasteryPersistenceException;

    public void changeOrder(Order order) throws FlooringMasteryPersistenceException;
    
    public Order getRemoveOrder(String date, String orderNumber) throws FlooringMasteryInvalidInputException,
            FlooringMasteryPersistenceException;

    public void removeOrder(Order order) throws FlooringMasteryPersistenceException;

    public void exportAllData() throws FlooringMasteryPersistenceException;

    public void exportBackupData() throws FlooringMasteryPersistenceException;
    
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException;
}
