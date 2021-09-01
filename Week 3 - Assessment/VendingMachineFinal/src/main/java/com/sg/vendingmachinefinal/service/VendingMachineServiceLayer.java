/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinefinal.service;

import com.sg.vendingmachinefinal.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinefinal.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Paul
 */
public interface VendingMachineServiceLayer {
    
    Item addItem(String name, Item item) throws VendingMachinePersistenceException;
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item getItem(String name) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineInvalidInventoryCountException;
    
    BigDecimal removeItem(String name, BigDecimal balance) throws 
            VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachineInvalidInventoryCountException;

    void updateInventory(String name, int newCount) throws VendingMachinePersistenceException, VendingMachineInvalidInventoryCountException, VendingMachineNoItemInventoryException;
}
