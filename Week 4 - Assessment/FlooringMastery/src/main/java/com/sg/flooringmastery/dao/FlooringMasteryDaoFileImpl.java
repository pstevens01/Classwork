/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.model.Tax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul
 */
@Component
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    Map<Integer, Order> orderMap = new HashMap<>();
    Map<String, Tax> taxMap = new HashMap<>();
    Map<String, Product> productMap = new HashMap<>();
    
    private static final String DELIMITER = "::";
    private final String ORDER_DIR, TAX_FILE, PRODUCT_FILE, BACKUP_FILE;
    
    public FlooringMasteryDaoFileImpl(String orderDir, String taxFile, String productFile, String backupFile) {
        this.ORDER_DIR = orderDir;
        this.TAX_FILE = taxFile;
        this.PRODUCT_FILE = productFile;
        this.BACKUP_FILE = backupFile;
    }

    @Autowired
    public FlooringMasteryDaoFileImpl() {
        ORDER_DIR = "Orders";
        TAX_FILE = "Taxes.txt";
        PRODUCT_FILE = "Products.txt";
        BACKUP_FILE = "Backup/DataExport.txt";
    }

    @Override
    public int addOrder(Order order) {
        // Searching for the highest order number of the map is not empty
        if(!orderMap.isEmpty()) {
            // Get the order with the highest order number
            Order highestOrderNum = getAllOrders().stream()
                    .max(Comparator.comparing((max) -> max.getOrderNumber())).get();
            int highestNum = highestOrderNum.getOrderNumber() + 1;
            
            // Add order to the map with the highest order number
            order.setOrderNumber(highestNum);
            orderMap.put(order.getOrderNumber(), order);
            return highestNum;
        } else {
            // No orders exist so order number will be 1
            order.setOrderNumber(1);
            orderMap.put(order.getOrderNumber(), order);
            return 1;
        }
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate date) {
        return getAllOrders().stream()
                .filter((order) -> order.getOrderDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrderByNumberAndDate(String orderNum, String date) {
        // Order number and date must match or return null
        if(orderMap.containsKey(Integer.parseInt(orderNum)) && 
                orderMap.get(Integer.parseInt(orderNum)).getOrderDate().equals(LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy")))) {
            return orderMap.get(Integer.parseInt(orderNum));
        } else {
            return null;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orderMap.values());
    }

    @Override
    public void editOrder(Order order) throws FlooringMasteryPersistenceException {
        orderMap.replace(order.getOrderNumber(), order);
        saveOrderData();
    }

    @Override
    public void removeOrder(Order order) {
        orderMap.remove(order.getOrderNumber(), order);
    }
    
    @Override
    public void updateOrder(Order order) throws FlooringMasteryPersistenceException {
        orderMap.replace(order.getOrderNumber(), order);
        saveOrderData();
    }
    
    @Override
    public void recalculateOrder(Order order) {
        order.setCostPerSqFt(productMap.get(order.getProductType()).getCostPerSqFt());
        order.setLaborCostPerSqFt(productMap.get(order.getProductType()).getLaborCostPerSqFt());
        order.setTaxRate(taxMap.get(order.getState()).getStateTaxRate());
        order.recalculate();
    }
    
    @Override
    public Order createOrder(String date, String customerName, String state, String productType, BigDecimal area) {
        return new Order(customerName, state, productType, LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy")), area, 
                taxMap.get(state).getStateTaxRate(), productMap.get(productType).getCostPerSqFt(), productMap.get(productType).getLaborCostPerSqFt());
    }
    
    // Return list of customer has multiple orders on the same day?
    @Override
    public Order getOrderByNameDate(String date, String customerName) {
        
        // Create a list of orders that matches the date and customerName params
        List<Order> orderList = getAllOrders().stream().filter((order) -> order.getOrderDate().equals(LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"))) 
                && order.getCustomerName().equals(customerName)).collect(Collectors.toList());
        Order order = null;
        if(!orderList.isEmpty()){
            order = orderList.get(0);
        }
        return order;
    }
    
    private Order unmarshallOrder(String orderAsText) {
        // orderAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1::Ada Lovelace::CA::25.00::Tile::249.00::3.50::4.15::871.50::1033.35::476.21::2381.06
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in orderTokens.
        // Which should look like this:
        // _______________________________________________________________________________________________________
        // | Order  | Cusomter | State | Tax  | Product | Area | Cost | Labor    | Material | Labor | Tax | Total |
        // | Number | Name     |       | Rate | Type    |      | Per  | Cost     | Cost     | Cost  |     |       |
        // |        |          |       |      |         |      | SqFt | Per SqFt |          |       |     |       |
        // -------------------------------------------------------------------------------------------------------
        //    [0]        [1]      [2]    [3]      [4]     [5]     [6]      [7]       [8]      [9]    [10]    [11]
        
        String orderTokens[] = orderAsText.split(DELIMITER);
        Order orderFromFile = new Order(orderTokens[0], orderTokens[1], orderTokens[2], new BigDecimal(orderTokens[3]), 
                orderTokens[4], new BigDecimal(orderTokens[5]), new BigDecimal(orderTokens[6]), new BigDecimal(orderTokens[7]), 
                new BigDecimal(orderTokens[8]), new BigDecimal(orderTokens[9]), new BigDecimal(orderTokens[10]), new BigDecimal(orderTokens[11]));
        return orderFromFile;
    }
    
    @Override
    public void loadOrderData() throws FlooringMasteryPersistenceException {
        File directory = new File(ORDER_DIR);
        String currentLine;
        Order order;
        
        try {
            for(File orderFile : directory.listFiles()) {
                Scanner in = new Scanner(new BufferedReader(new FileReader(orderFile)));
                in.nextLine();
                
                while(in.hasNextLine()) {
                    currentLine = in.nextLine();
                    order = unmarshallOrder(currentLine);
                    // Get order date from file name
                    String orderDateString = orderFile.getName().substring(7, 15);
                    // Parse to LocalDate
                    LocalDate orderDate = LocalDate.parse(orderDateString, DateTimeFormatter.ofPattern("MMddyyyy"));
                    // Format to data export file format
                    orderDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                    order.setOrderDate(orderDate);
                    orderMap.put(order.getOrderNumber(), order);
                }
                in.close();
            }
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Couldn't read file");
        }
    }
    
    private String marshallOrder(Order order) {
        // We need to turn a Order object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 1::Ada Lovelace::CA::25.00::Tile::249.00::3.50::4.15::871.50::1033.35::476.21::2381.06

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer.
        
        String orderString = order.getOrderNumber() + DELIMITER
                + order.getCustomerName() + DELIMITER
                + order.getState() + DELIMITER
                + order.getTaxRate() + DELIMITER
                + order.getProductType() + DELIMITER
                + order.getArea() + DELIMITER
                + order.getCostPerSqFt() + DELIMITER
                + order.getLaborCostPerSqFt() + DELIMITER
                + order.getMaterialCost() + DELIMITER
                + order.getLaborCost() + DELIMITER
                + order.getTotalTax() + DELIMITER
                + order.getOrderTotal();
        return orderString;
    }
    
    @Override
    public void saveOrderData() throws FlooringMasteryPersistenceException {
        // Orders mapped by order date as key
        Map<LocalDate, List<Order>> mappedOrdersByDate = getAllOrders().stream()
                .collect(Collectors.groupingBy(order -> order.getOrderDate()));
        
        PrintWriter out;
        String orderAsText;
        try {
            File directory = new File(ORDER_DIR);
            for (File file : directory.listFiles()) {
                file.delete();
            }
            
            for (List<Order> orderList : mappedOrdersByDate.values()) {
                String date = orderList.get(0).getOrderDate().format(DateTimeFormatter.ofPattern("MMddyyyy"));
                
                // Create file using date as identifier
                out = new PrintWriter(new FileWriter(new File(ORDER_DIR, "Orders_" + date + ".txt")));
                
                // Header
                out.println("OrderNumber" + DELIMITER + "CustomerName" + DELIMITER + "State" + DELIMITER + "TaxRate" + DELIMITER + "ProductType"
                        + DELIMITER + "Area" + DELIMITER + "CostPerSquareFoot" + DELIMITER + "LaborCostPerSquareFoot"
                        + DELIMITER + "MaterialCost" + DELIMITER + "LaborCost" + DELIMITER + "Tax" + DELIMITER + "Total");
                out.flush();
                
                // Write each order under each date key
                for (Order order : orderList) {
                    orderAsText = marshallOrder(order);
                    out.println(orderAsText);
                    out.flush();
                }
                out.close();
            }
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could't write to file");
        }
    }
    
    private Product unmarshallProduct(String productAsText) {
        String[] productTokens = productAsText.split(DELIMITER);
        Product productFromFile = new Product(productTokens[0], new BigDecimal(productTokens[1]), new BigDecimal(productTokens[2]));
        return productFromFile;
    }
   
    public void loadProductData() throws FlooringMasteryPersistenceException {
        
        Scanner in;
        
        try {
            in = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not read product file");
        }
        
        in.nextLine();
        
        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            Product currentProduct = unmarshallProduct(currentLine);
            productMap.put(currentProduct.getProductType(), currentProduct);
        }
        in.close();
    }
    
    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(productMap.values());
    }
    
    @Override
    public boolean hasProductType(String product) {
        // Returns true if the product map contains the product param
        return productMap.containsKey(product);
    }
    
    private Tax unmarshallTaxData(String taxAsText) {
        String[] taxTokens = taxAsText.split(DELIMITER);
        Tax taxFromFile = new Tax(taxTokens[0], taxTokens[2], new BigDecimal(taxTokens[2]));
        return taxFromFile;
    }
    
    @Override
    public void loadTaxData() throws FlooringMasteryPersistenceException {
        
        Scanner in;
        
        try {
            in = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not read tax file");
        }
        
        in.nextLine();
        
        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            Tax currentTax = unmarshallTaxData(currentLine);
            taxMap.put(currentTax.getStateAbbr(), currentTax);
        }
        in.close();
    }
    
    @Override
    public boolean hasTaxCode(String abbv) {
        // Returns true if the tax map contains the state abbreviation param
        return taxMap.containsKey(abbv);
    }
    
    // Marshall order data for exporting to backup
    private String marshallOrdersForExport(Order order) {
        String orderString = order.getOrderNumber() + DELIMITER 
                + order.getCustomerName() + DELIMITER
                + order.getState() + DELIMITER 
                + order.getTaxRate() + DELIMITER
                + order.getProductType() + DELIMITER 
                + order.getArea() + DELIMITER 
                + order.getCostPerSqFt() + DELIMITER 
                + order.getLaborCostPerSqFt() + DELIMITER 
                + order.getMaterialCost() + DELIMITER 
                + order.getLaborCost() + DELIMITER 
                + order.getTotalTax()+ DELIMITER  
                + order.getOrderTotal()+ DELIMITER + order.getOrderDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        return orderString;
    }
    
    @Override
    public void exportOrdersForBackup() throws FlooringMasteryPersistenceException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(BACKUP_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Couldn't write to file");
        }
        
        // Header
        out.println("OrderNumber" + DELIMITER + "CustomerName" + DELIMITER + "State" + DELIMITER + "TaxRate" + DELIMITER + "ProductType"
                + DELIMITER + "Area" + DELIMITER + "CostPerSquareFoot" + DELIMITER + "LaborCostPerSquareFoot"
                + DELIMITER + "MaterialCost" + DELIMITER + "LaborCost" + DELIMITER + "Tax" + DELIMITER + "Total" + DELIMITER + "Date");
        out.flush();
        
        List<Order> orderList = this.getAllOrders();
        
        for (Order order : orderList) {
            String orderAsText = marshallOrdersForExport(order);
            out.println(orderAsText);
            out.flush();
        }
        out.close();
    }
}
