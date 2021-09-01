/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.vendingmachinefinal.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachinefinal.dto.Item;
import java.io.FileWriter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sg.vendingmachinefinal.dao.VendingMachineDao;
import java.math.BigDecimal;

/**
 *
 * @author Paul
 */
public class VendingMachineDaoFileImplTest {
    
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testVendingMachine.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    }

    @Test
    public void testGetItem() throws Exception {
        // Create out method test inputs
        String name = "Chips";
        Item item = new Item(name, new BigDecimal("2.00"), 7);
        
        // Add item to be retrieved
        testDao.addItem(item.getName(), item); 
        
        // Get the Item from the DAO
        Item retrievedItem = testDao.getItem(name);
        
        // Check the data is equal
        assertEquals(item.getName(), retrievedItem.getName(),
                "Checking name.");
        assertEquals(item.getCost(), retrievedItem.getCost(),
                "Checking cost.");
        assertEquals(item.getInvCount(), retrievedItem.getInvCount(),
                "Checking inventory count.");
    }
    
    @Test
    public void testGetAllItems() throws Exception {
        // Create first item
        String firstName = "Chips";
        Item firstItem = new Item(firstName, new BigDecimal("2.00"), 7);
        
        // Create second item
        String secondName = "Soda";
        Item secondItem = new Item(secondName, new BigDecimal("2.50"), 5);
        
        // Add both items to the DAO
        testDao.addItem(firstItem.getName(), firstItem);
        testDao.addItem(secondItem.getName(), secondItem);
        
        // Retrieve the list of all items within the DAO
        List<Item> allItems = testDao.getAllItems();
        
        // First check the general contents of the list
        assertNotNull(allItems, "The list of items must not be null");
        assertEquals(2, allItems.size(), "List of items should have 2 items.");
        
        // Then the specifics
        assertTrue(testDao.getAllItems().contains(firstItem),
                "The list of item should include Chips.");
        assertTrue(testDao.getAllItems().contains(secondItem),
                "The list of items should include Soda.");
    }
     
    // Add remove item tests
    @Test
    public void testRemoveItem() throws Exception {
        // Create first item
        String firstName = "Chips";
        Item firstItem = new Item(firstName, new BigDecimal("2.00"), 7);
        
        // Create second item
        String secondName = "Soda";
        Item secondItem = new Item(secondName, new BigDecimal("2.50"), 5);
        
        testDao.addItem(firstItem.getName(), firstItem);
        testDao.addItem(secondItem.getName(), secondItem);
        
        // Remove the first item - Chips
        Item removedItem = testDao.removeItem(firstItem.getName());
        
        // Check that that correct object was removed.
        assertEquals(removedItem, firstItem, "The removed item should be Chips.");
        
        // Get all the items
        List<Item> allItems = testDao.getAllItems();
        
        // First check the general contents of the list
        assertNotNull(allItems, "All items list should not be null.");
        assertEquals(1, allItems.size(), "All items should only have 1 item.");
        
        // Then the specifics
        assertFalse(allItems.contains(firstItem), "All items should NOT have Chips.");
        assertTrue(allItems.contains(secondItem), "All items should have Soda");
        
        // Remove the second item
        removedItem = testDao.removeItem(secondItem.getName());
        
        // Check that the correct object was removed
        assertEquals(removedItem, secondItem, "The removed item should be Soda");
        
        // Retrieve all of the items again, and check the list.
        allItems = testDao.getAllItems();
        
        // Check the contents of the list - it should be empty
        assertTrue(allItems.isEmpty(), "The retrieved list of items should be empty");
        
        // Try to 'get' both items by their old name - they should be null
        Item retrievedItem = testDao.getItem(firstItem.getName());
        assertNull(retrievedItem, "Chips was removed, should be null");
        
        retrievedItem = testDao.getItem(secondItem.getName());
        assertNull(retrievedItem, "Soda was removed, should be null");
    }
 
    @Test
    public void testUpdateItemInventoryCount() throws Exception {
        // Create test item
        String name = "Chips";
        Item item = new Item(name);
        item.setCost(new BigDecimal("2.00"));
        item.setInvCount(7);
        
        // Add it to the DAO
        testDao.addItem(item.getName(), item);
        
        // Update item inventory count
        testDao.updateItem(item.getName(), 6);
        
        // Retrieve item
        Item revtriveItem = testDao.getItem(name);
        
        // Check if item inventory count was updated
        assertEquals(revtriveItem.getInvCount(), 6, "Inventory count should be the updated count of 6.");
    }
    
}

