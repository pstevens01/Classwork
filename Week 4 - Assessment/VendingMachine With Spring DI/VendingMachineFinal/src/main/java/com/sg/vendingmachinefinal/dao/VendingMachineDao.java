/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinefinal.dao;

import com.sg.vendingmachinefinal.dto.Item;
import com.sg.vendingmachinefinal.service.VendingMachineInvalidInventoryCountException;
import com.sg.vendingmachinefinal.service.VendingMachineNoItemInventoryException;
import java.util.List;

/**
 *
 * @author Paul
 */
public interface VendingMachineDao {

    /**
     * Adds the given Item to the inventory and associates it with the given
     * name. If there is already a item associated with the given
     * name it will return that Item object, otherwise it will
     * return null. This method will only be used for Unit Testing.
     *
     * @param name the name of the item being added to the vending machine
     * @param item the item object being added to the vending machine
     * @return the Item object previously associated with the given 
     * name if it exists, null otherwise
     */
    Item addItem(String name, Item item) throws VendingMachinePersistenceException;

    /**
     * Returns a List of all Items in the vending machine.
     *
     * @return List containing all items in the vending machine.
     */
    List<Item> getAllItems() throws VendingMachinePersistenceException;

    /**
     * Returns the Item object associated with the given name.
     * Returns null if no such item exists
     *
     * @param name name of the item ob to be retrieved
     * @return the Item object associated with the given name,
     * null if no such item exists
     */
    Item getItem(String name) throws VendingMachinePersistenceException, VendingMachineInvalidInventoryCountException;

    /**
     * Removes from the vending machine the item associated with the given name.
     * Returns the Item object that is being removed or null if
     * there is no item associated with the given name
     *
     * @param name name of the item being removed
     * @return Item object that was removed or null if no item
     * was associated with the given name
     */
    Item removeItem(String name) throws VendingMachinePersistenceException;
    
    /**
     * Update the new inventory count of the vending machine items associated with
     * the given name.
     * 
     * @param name name of item to be updated
     * @param newCount new inventory count
     */
    void updateItem(String name, int newCount) throws VendingMachinePersistenceException, VendingMachineInvalidInventoryCountException, VendingMachineNoItemInventoryException;
}
