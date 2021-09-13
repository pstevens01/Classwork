/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringMasteryDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul
 */
@Component
public class FlooringMasteryServiceLayerImplTest {
    
    private FlooringMasteryServiceLayer service;
    
    @Autowired
    public FlooringMasteryServiceLayerImplTest() {
        FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
        FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
        
        service = new FlooringMasteryServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetOrdersByDate() {
        List<Order> ordersByDate = null;
        try{
            ordersByDate = service.getOrdersByDate("09-11-2022");
        }
        catch(FlooringMasteryPersistenceException | FlooringMasteryInvalidInputException e){
            fail("Exception should not occur");
        } finally{
            assertNotNull(ordersByDate);
        }
    }
        
    @Test
    public void testGetAllOrders() {
        List<Order> orderList = null;
        try {
            orderList = service.getAllOrders();
        } catch (FlooringMasteryPersistenceException e) {
            fail("Exception should not occur");
        } finally {
            assertNotNull(orderList);
            assertEquals(2, orderList.size(), "List should contain 3 orders");
        }
    }
    
    @Test
    public void testAddGetOrder() {
        Order testOrder = null;
        Order newOrder = new Order();
        newOrder.setOrderNumber(3);
        newOrder.setCustomerName("Theodore Stevens");
        newOrder.setState("TX");
        newOrder.setTaxRate(BigDecimal.ONE);
        newOrder.setProductType("Wood");
        newOrder.setArea(BigDecimal.ONE);
        newOrder.setCostPerSqFt(BigDecimal.ONE);
        newOrder.setLaborCostPerSqFt(BigDecimal.ONE);
        newOrder.setMaterialCost(BigDecimal.ONE);
        newOrder.setLaborCost(BigDecimal.ONE);
        newOrder.setTotalTax(BigDecimal.ONE);
        newOrder.setOrderTotal(new BigDecimal("1.23"));
        LocalDate date = LocalDate.parse("09-11-2021", DateTimeFormatter.ofPattern("MM-dd-yyyy")); 
        newOrder.setOrderDate(date);
           
        try {
            service.submitOrder(newOrder);
            testOrder = service.getOrder("09-11-2021", "3");
        } catch (FlooringMasteryPersistenceException | FlooringMasteryInvalidInputException e) {
            fail("Exception should no occur");
        } finally {
            assertNotNull(testOrder);
            assertEquals(testOrder, newOrder, "These two orders should be equal");
        }
    }
    
    @Test
    public void testRemoveOrder() {
        Order removedOrder = null;
        try {
            removedOrder = service.getOrder("09-11-2021", "1");
            service.removeOrder(removedOrder);
        } catch (FlooringMasteryPersistenceException | FlooringMasteryInvalidInputException e) {
            fail("No exception should be thrown");
        } finally {
            assertEquals(removedOrder.getCustomerName(), "Paul Stevens");
            assertEquals(removedOrder.getState(), "TX");
            assertEquals(removedOrder.getProductType(), "Wood");
        }
    }
    
    /**
    @Test
    public void testEditOrder() {
        String editName = "Edit Name";
        String editState = "KY";
        String editProduct = "Other Product";
        String editArea = "200";
        
        Order order = null;
        
        try {
            order = service.getOrder("09-11-2021", "1");
            assertNotNull(order); 
            
        } catch (FlooringMasteryPersistenceException | FlooringMasteryInvalidInputException e) {
            fail("No exception should be thrown");
        } finally {

        }
    }
    **/

}
