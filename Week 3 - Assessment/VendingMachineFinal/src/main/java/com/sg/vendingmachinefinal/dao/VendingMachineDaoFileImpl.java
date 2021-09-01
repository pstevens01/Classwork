/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinefinal.dao;

import com.sg.vendingmachinefinal.dto.Item;
import com.sg.vendingmachinefinal.service.VendingMachineInvalidInventoryCountException;
import com.sg.vendingmachinefinal.service.VendingMachineNoItemInventoryException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    private Map<String, Item> inventory = new HashMap<>();
    
    private final String INV_FILE;
    public static final String DELIMITER = "::";
    
    public VendingMachineDaoFileImpl() {
        INV_FILE = "inventory.txt";
    }
    
    public VendingMachineDaoFileImpl(String invTextFile) {
        INV_FILE = invTextFile;
    }

    @Override
    public Item addItem(String name, Item item) throws VendingMachinePersistenceException {
        loadRoster();
        Item prevItem = inventory.put(name, item);
        writeRoster();
        return prevItem;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadRoster();
        return new ArrayList<Item>(inventory.values());
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException,
            VendingMachineInvalidInventoryCountException {
        loadRoster();
        return inventory.get(name);
    }

    @Override
    public Item removeItem(String name) throws VendingMachinePersistenceException {
        loadRoster();
        Item removedItem = inventory.remove(name);
        writeRoster();
        return removedItem;
    }
    
    // Could have included this function within the removeItem method / might not even need this

    @Override
    public void updateItem(String name, int newCount) throws VendingMachinePersistenceException, VendingMachineInvalidInventoryCountException, VendingMachineNoItemInventoryException {
        getItem(name).setInvCount(newCount);
        writeRoster();
    }
    
    private Item unmarshallItem(String itemAsText){
        // itemAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // Chips::1.25::7
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in itemTokens.
        // Which should look like this:
        // ___________________
        // |    |     |      |
        // |name|price|count |
        // |    |     |      |                
        // -------------------
        //  [0]  [1]    [2]     
        String[] itemTokens = itemAsText.split(DELIMITER);

        // Given the pattern above, the Item name is in index 0 of the array.
        String name = itemTokens[0];

        // Which we can then use to create a new Item object to satisfy
        // the requirements of the Item constructor.
        Item itemFromFile = new Item(name);

        // However, there are 2 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.

        // Index 1 - Cost
        itemFromFile.setCost(new BigDecimal(itemTokens[1]));

        // Index 2 - Inventory Count
        itemFromFile.setInvCount(Integer.parseInt(itemTokens[2]));

        // We have now created an Item! Return it!
        return itemFromFile;
    }
    
    private void loadRoster() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INV_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        Item currentItem;
        // Go through INV_FILE line by line, decoding each line into a 
        // Item object by calling the unmarshallItem method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Item
            currentItem = unmarshallItem(currentLine);

            // We are going to use the Item name as the map key for our Item object.
            // Put currentItem into the map using name as the key
            inventory.put(currentItem.getName(), currentItem);
        }
        // close scanner
        scanner.close();
    }
    
    private String marshallItem(Item anItem){
        // We need to turn a Item object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // Candy Bar::0.75::4

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 

        // Start with the item name, since that's supposed to be first.
        String itemAsText = anItem.getName() + DELIMITER;

        // add the rest of the properties in the correct order:

        // Cost
        itemAsText += anItem.getCost() + DELIMITER;

        // Inventory Count - don't forget to skip the DELIMITER here.
        itemAsText += anItem.getInvCount();

        // We have now turned a student to text! Return it!
        return itemAsText;
    }
    
    /**
    * Writes all vending machine items in the roster out to a ROSTER_FILE. See 
    * loadRoster for file format.
    * 
    * @throws VendingMachinePersistenceException if an error occurs writing to the file
    */
    private void writeRoster() throws VendingMachinePersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INV_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save student data.", e);
        }

        // Write out the Item objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the inventory map,
        // get the Collection of Items and iterate over them but we've
        // already created a method that gets a List of Items so
        // we'll reuse it.
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            // turn a Item into a String
            itemAsText = marshallItem(currentItem);
            // write the Item object to the file
            out.println(itemAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
        }
}
