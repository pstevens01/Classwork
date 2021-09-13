/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul
 */
@Component
public class FlooringMasteryDaoFileImplTest {
    
    FlooringMasteryDao testDao;
    
    public FlooringMasteryDaoFileImplTest() {
        
    }
  
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        testDao = new FlooringMasteryDaoFileImpl("TestOrders", "Taxes.txt", "Products.txt", "TestBackup/testDataExport.txt");
        testDao.loadOrderData();
        testDao.loadProductData();
        testDao.loadTaxData();
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testAddGetAllOrders() {
        // Create first order
        Order firstOrder = testDao.createOrder("09-11-2021", "Paul Stevens", "TX", "Carpet", new BigDecimal("500"));
        // Create second order
        Order secondOrder = testDao.createOrder("09-12-2021", "Penelope Stevens", "TX", "Wood", new BigDecimal("700"));
        
        testDao.addOrder(firstOrder);
        testDao.addOrder(secondOrder);
        
        // Retrieve the list of all items within the DAO
        List<Order> allItems = testDao.getAllOrders();
        
        // First check the general contents of the list
        assertNotNull(allItems, "The list of orders must not be null");
        assertEquals(5, allItems.size(), "List of orders should have 5 items.");
        
        // Then the specifics
        assertTrue(testDao.getAllOrders().contains(firstOrder),
                "The list of orders should include the first order.");
        assertTrue(testDao.getAllOrders().contains(secondOrder),
                "The list of items should include the second order.");
    }
    
    @Test
    public void testGetOrderByDate() {
        List<Order> orderList = testDao.getOrdersByDate(LocalDate.parse("06-02-2013", DateTimeFormatter.ofPattern("MM-dd-yyyy")));
        assertEquals(2, orderList.size());
    }
    
    /**
    @Test
    public void testGetOrderByNameDate() {
        Order testOrder = testDao.getOrderByNameDate("06-01-2013", "Ada Lovelace");
        assertNotNull(testOrder);
    }
    **/
}
