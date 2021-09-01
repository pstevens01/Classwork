/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinefinal.ui;

import com.sg.vendingmachinefinal.dto.Change;
import com.sg.vendingmachinefinal.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Paul
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayMainBanner() {
        io.print("======= VENDING MACHINE =======");
    }
    public void displayVendingMachineContents(List<Item> itemList) {
        for (Item currentItem : itemList) {
            io.print(currentItem.getName() + "\t" + currentItem.getCost() +
                    "\t" + currentItem.getInvCount()); 
        }
    }
    
    public String getItemChoice() {
        return io.readString("Please enter the name of the item you wish to purchase. Or \'E\' to exit.");
    }
    
    public void displayInsufficentFundsBanner() {
        io.print("Insufficient Funds.");
    }
    
    public void displayOutOfStockBanner() {
        io.print("Item Not In Stock.");
    }
    
    public void displayChoosenItem(Item item) {
        io.print("Item you would like to purchase: " + item.getName());
        io.print("Price: " + item.getCost());
    }
    
    public void displayBalance(BigDecimal balance) {
        io.print("Current Balance: $" + balance); 
    }
    
    public String getDepositAmount() {
        return io.readString("Please enter the amount of money you wish to deposit into the "
                + "vending machine (Example 2.50). Or press \'E\' to exit.");
    }
    
    public void displayChangeAndCoins(BigDecimal cost, BigDecimal change, Change ch) {
        io.print("Change Due: $" + change);
        ch.getChange(cost);
        // io.print("Quarters: " + String.valueOf(ch.getQuarters()));
        io.print("Quarters: " + ch.getQuarters());
        io.print("Dimes: " + ch.getDimes());
        io.print("Nickles: " + ch.getNickles());
        io.print("Pennies: " + ch.getPennies());
    }
    
    // Move logic to controller??? 
    public boolean makeAnotherPurchase() {
        boolean keepVending = true;
        String input = io.readString("Would you like to make another purchase? (Y/N)");
        switch (input) {
            case "y":
            case "Y":
                return keepVending;
            case "n":
            case "N":
                keepVending = false;
                break;
            default:
                displayUnknownCommandBanner();
                keepVending = false;
        }
        return keepVending;
    }
    
    public void displayRemoveItemResults(Item item) {
        if(item != null){
          io.print("Item successfully removed.");
        }else{
          io.print("No such Item.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
