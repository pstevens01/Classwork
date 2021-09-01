/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

import com.sg.vendingmachinefinal.dto.Item;
import java.util.List;
import com.sg.vendingmachinefinal.dao.VendingMachineDao;
import com.sg.vendingmachinefinal.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinefinal.service.VendingMachineInvalidInventoryCountException;
import com.sg.vendingmachinefinal.service.VendingMachineNoItemInventoryException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Paul
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    public Item onlyItem;
    
    public VendingMachineDaoStubImpl() {
        String name = "Chips";
        Item item = new Item(name, new BigDecimal("2.00"), 7);
        this.onlyItem = item;
    }
    
    public VendingMachineDaoStubImpl(Item testItem){
         this.onlyItem = testItem;
     }

    @Override
    public Item addItem(String name, Item item) throws VendingMachinePersistenceException {
        if (name.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        if (name.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return  null;
        }
    }

    @Override
    public Item removeItem(String name) throws VendingMachinePersistenceException {
        if (name.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void updateItem(String name, int newCount) throws VendingMachineNoItemInventoryException, VendingMachinePersistenceException, VendingMachineInvalidInventoryCountException {
        Item updateItem = getItem(name);
        updateItem.setInvCount(newCount);
    }
}
