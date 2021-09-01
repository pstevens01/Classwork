/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinefinal.service;

import com.sg.vendingmachinefinal.dto.Item;
import java.util.List;
import com.sg.vendingmachinefinal.dao.VendingMachineAuditDao;
import com.sg.vendingmachinefinal.dao.VendingMachineDao;
import com.sg.vendingmachinefinal.dao.VendingMachinePersistenceException;
import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 *
 * @author Paul
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Item addItem(String name, Item item) throws VendingMachinePersistenceException {
        Item addedItem = dao.addItem(name, item);
        return addedItem;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems().stream()
                .collect(Collectors.toList());
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineInvalidInventoryCountException {
        return dao.getItem(name);
    }
    
    @Override
    public void updateInventory(String name, int newCount) throws VendingMachinePersistenceException, VendingMachineInvalidInventoryCountException, VendingMachineNoItemInventoryException {
        if (newCount < 0) {
            throw new VendingMachineInvalidInventoryCountException("New Inventory Count is Invalid!");
        }
        dao.updateItem(name, newCount);
    }

    @Override
    public BigDecimal removeItem(String name, BigDecimal balance) throws 
            VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachineInvalidInventoryCountException {
        
        Item removedItem = getItem(name);
        
        if (balance.compareTo(removedItem.getCost()) == -1) {
            throw new VendingMachineInsufficientFundsException("Insufficient Funds!");
        } else if (removedItem.getInvCount() <= 0) {
            throw new VendingMachineNoItemInventoryException("Out of Stock!");
        }
        
        updateInventory(name, removedItem.getInvCount() - 1);
        
        // Write to audit log with successful removal of student
        auditDao.writeAuditEntry(
            "Item: " + name + " PURCHASED.");
        return balance.subtract(removedItem.getCost());
    }
}
