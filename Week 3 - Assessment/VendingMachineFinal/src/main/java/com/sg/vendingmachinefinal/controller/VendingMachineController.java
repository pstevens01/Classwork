/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinefinal.controller;

import com.sg.vendingmachinefinal.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinefinal.dto.Change;
import com.sg.vendingmachinefinal.dto.Item;
import com.sg.vendingmachinefinal.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinefinal.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachinefinal.ui.VendingMachineView;
import java.util.List;
import com.sg.vendingmachinefinal.service.VendingMachineServiceLayer;
import java.math.BigDecimal;

/**
 *
 * @author Paul
 */
public class VendingMachineController {
    
    private VendingMachineServiceLayer service;
    private VendingMachineView view;
    private BigDecimal currentBalance;
    

    public void run() {
        boolean keepGoing = true;
        
        try {
            displayVendingMachineBanner();
            listItems();
            getDepositAmount();
            
            while (keepGoing) {
                displayCurrentBalance();
                displayVendingMachineBanner();
                listItems();
                
                String choice = getItemChoice();
                
                try {
                    Item chosenItem = getItem(choice);
                    removeItem(chosenItem);
                } catch (VendingMachineInsufficientFundsException e) {
                    
                } catch (VendingMachineNoItemInventoryException e) {
                    
                }
            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    private void displayVendingMachineBanner() {
        view.displayMainBanner();
    }
    
    private void listItems() throws VendingMachinePersistenceException {
        List<Item> itemList = service.getAllItems();
        view.displayVendingMachineContents(itemList);
    }
    
    // Move currentBalance variable to a DTO with DAO/Service Layer impl?
    private void getDepositAmount() {
        String amount = view.getDepositAmount();
        if (amount.equalsIgnoreCase("e")) {
            System.exit(0);
        }
        
        BigDecimal amountDeposited = new BigDecimal(amount);
        currentBalance = amountDeposited;
    }
    
    // Move currentBalance variable to a DTO with DAO/Service Layer impl?
    private void displayCurrentBalance() {
        // BigDecimal currentBalance = service.getBalance;??
        view.displayBalance(currentBalance);
    }
    
    private String getItemChoice() {
        String choice = view.getItemChoice();
        if (choice.equalsIgnoreCase("e")) {
            System.exit(0);
        }
        return choice;
    }
    
    private Item getItem(String choice) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        Item itemChoice = service.getItem(choice);
        view.displayChoosenItem(itemChoice);
        return itemChoice;
    }
    
    // BigDecimal, send to memorty storage of currentBalance?
    private void removeItem(Item item) throws 
            VendingMachinePersistenceException, VendingMachineNoItemInventoryException, VendingMachineInsufficientFundsException {
        BigDecimal change = service.removeItem(item.getName(), currentBalance);
        view.displayRemoveItemResults(item);
        if (change.compareTo(BigDecimal.ZERO) != 0) {
            Change coinChange = new Change();
            view.displayChangeAndCoins(item.getCost(), change, coinChange);
        }
        currentBalance = currentBalance.subtract(item.getCost());
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}
