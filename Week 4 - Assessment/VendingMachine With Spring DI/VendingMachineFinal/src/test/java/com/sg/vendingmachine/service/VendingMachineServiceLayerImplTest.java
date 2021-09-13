/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachinefinal.service.VendingMachineServiceLayerImpl;
import com.sg.vendingmachinefinal.dto.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sg.vendingmachinefinal.dao.VendingMachineAuditDao;
import com.sg.vendingmachinefinal.dao.VendingMachineDao;
import com.sg.vendingmachinefinal.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinefinal.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinefinal.service.VendingMachineInvalidInventoryCountException;
import com.sg.vendingmachinefinal.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachinefinal.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul
 */
@Component
public class VendingMachineServiceLayerImplTest {
    
    private VendingMachineServiceLayer service;
    
    @Autowired
    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
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
    public void testGetItem() throws Exception {
        // ARRANGE
        Item testItem = new Item("Chips");
        testItem.setCost(new BigDecimal("2.00"));
        testItem.setInvCount(7);
        
        // ACT & ASSERT
        Item shouldBeChips = service.getItem("Chips");
        assertNotNull(shouldBeChips, "Getting Chips should not be null");
        assertEquals(testItem, shouldBeChips, "Item stored under Chips should be Chips");
        
        Item shouldBeNull = service.getItem("Candy");
        assertNull(shouldBeNull, "Getting Candy should be null");
    }

    @Test
    public void testGetAllItems() throws Exception {
        // ARRANGE
        Item testItem = new Item("Chips");
        testItem.setCost(new BigDecimal("2.00"));
        testItem.setInvCount(7);
        
        // ACT & ASSERT
        assertEquals(1, service.getAllItems().size(),
                "Should only have one item.");
        assertTrue(service.getAllItems().contains(testItem),
                "The one item should be Chips.");
    }
    
    @Test
    public void testRemoveItem() throws Exception {
        // ARRANGE
        Item testItem = new Item("Chips");
        testItem.setCost(new BigDecimal("2.00"));
        testItem.setInvCount(7);
        BigDecimal balance = new BigDecimal("3.00");

        // ACT & ASSERT
        BigDecimal change = service.removeItem(testItem.getName(), balance);
        assertNotNull(change, "Change should be not null in this case");
        assertEquals(change, new BigDecimal("1.00"), "Change should be equal to 1");
    }
    
    @Test
    public void testRemoveItemZeroInventory() throws Exception {
        // ARRANGE
        Item testItem = new Item("Chips");
        testItem.setCost(new BigDecimal("2.00"));
        testItem.setInvCount(0);
        BigDecimal balance = new BigDecimal("3.00");

        // ACT
        try {
            service.removeItem(testItem.getName(), balance);
        } catch (VendingMachineInsufficientFundsException
                | VendingMachinePersistenceException e) {
        // ASSERT
            fail("Incorrect exception was thrown");
        } catch (VendingMachineNoItemInventoryException e) {
            return;
        }
    }
    
    @Test
    public void testInvaidInventoryUpdate() throws Exception {
        // ARRANGE
        Item testItem = new Item("Chips");
        testItem.setCost(new BigDecimal("2.00"));
        testItem.setInvCount(7);

        //ACT & ASSERT
        try {
            service.updateInventory(testItem.getName(), -1);
            fail("Expected InvalidInventoryCount exception was not thrown");
        } catch (VendingMachinePersistenceException
                | VendingMachineInvalidInventoryCountException e) {
            return;
        }
    }
    
    @Test
    public void testInsufficientFunds() throws Exception {
        // ARRANGE
        Item testItem = new Item("Chips");
        testItem.setCost(new BigDecimal("2.00"));
        testItem.setInvCount(7);
        BigDecimal balance = new BigDecimal("1.00");
 
        //ACT & ASSERT
        try {
            service.removeItem(testItem.getName(), balance);
            fail("Expected InsufficientFunds exception was not thrown");
        } catch (VendingMachineInvalidInventoryCountException
                | VendingMachineNoItemInventoryException
                | VendingMachinePersistenceException e) {
            fail("Incorrect exception was thrown");
        } catch (VendingMachineInsufficientFundsException e) {
            return;
        }
    }
}
