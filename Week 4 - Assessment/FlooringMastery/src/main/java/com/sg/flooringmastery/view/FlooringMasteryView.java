/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.model.Product;
import com.sg.flooringmastery.userIO.UserIO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul
 */
@Component
public class FlooringMasteryView {
    
    UserIO io;
    
    @Autowired
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print(">>>>> FLOORING ORDER PROGRAM <<<<<");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");
        io.print(">>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<");
        return io.readInt("Please make a selection", 1, 6);
    }
    
    public void displayOrdersBanner() {
        io.print(">>>>> Display Orders <<<<<");
    }
    
    public void displayOrderSummaryBanner() {
        io.print(">>>>> Order Summary <<<<<");
    }
    
    public void displayEditOrderBanner() {
        io.print(">>>>> Edit Order <<<<<");
    }
    
    public void displayOrders(List<Order> orderList) {
        for (Order order : orderList) {
            io.print("Order Number: " + order.getOrderNumber() +
                    "\nOrder Date: " + order.getOrderDate() +
                    "\nCustomer Name: " + order.getCustomerName() +
                    "\nState: " + order.getState() +
                    "\nTax Rate: " + order.getTaxRate() +
                    "\nProduct Type: " + order.getProductType() +
                    "\nArea: " + order.getArea() +
                    "\nCost per Square Ft: " + order.getCostPerSqFt() +
                    "\nLaber Cost per Square Ft: " + order.getLaborCostPerSqFt() +
                    "\nMaterial Cost: " + order.getMaterialCost() +
                    "\nLabor Cost: " + order.getLaborCost() +
                    "\nTax: " + order.getTotalTax() +
                    "\nTotal: " + order.getOrderTotal());
            io.print("--------------------------------------------------------");
        }
    }
    
    public void displayOrder(Order order) {
        io.print("Order Number: " + order.getOrderNumber() +
                "\nOrder Date: " + order.getOrderDate() +
                "\nCustomer Name: " + order.getCustomerName() +
                "\nState: " + order.getState() +
                "\nTax Rate: " + order.getTaxRate() +
                "\nProduct Type: " + order.getProductType() +
                "\nArea: " + order.getArea() +
                "\nCost per Square Ft: " + order.getCostPerSqFt() +
                "\nLaber Cost per Square Ft: " + order.getLaborCostPerSqFt() +
                "\nMaterial Cost: " + order.getMaterialCost() +
                "\nLabor Cost: " + order.getLaborCost() +
                "\nTax: " + order.getTotalTax() +
                "\nTotal: " + order.getOrderTotal());
    }
    
    public String getListProductType(List<Product> productList) {
        for (Product product : productList) {
            io.print("Product Type: " + product.getProductType() +
                    "\nCost Per Sq Ft: " + product.getCostPerSqFt() +
                    "\nLabor Cost Per Sq Ft: " + product.getLaborCostPerSqFt());
            io.print("");
        }
        return io.readString("Enter the name of the product for the order:");
    }
    
    public void displayEditInstructions() {
        io.print("Enter new order details. If you don't want to edit a certain field just press enter.");
    }
    
    public String getOrderNumber() {
        return io.readString("Enter an order number:");
    }
    
    public String getOrderDate() {
        return io.readString("Please enter the date of the order (mm-dd-yyyy):");
    }
    
    public String getCustomerName() {
        return io.readString("Enter customer name:");
    }
    
    public String getState() {
        return io.readString("Enter state abbreviation (NY, CA, FL):").toUpperCase();
    }
    
    public String getArea() {
        return io.readString("Enter the amount of product to be purchased in square feet:");
    }
    
    public String displayOrderConfirmation() {
        return io.readString("Would you like to place the order (Y/N)?").toUpperCase();
    }
    
    public String displayRemoveOrderConfirmation() {
        return io.readString("Would you like to remove this order (Y/N)?").toUpperCase();
    }
    
    public void displayOrderCreationSuccess() {
        io.print("Order created successfully.");
    }
    
    public void displayRemoveOrderSuccess() {
        io.print("Order removed successfully.");
    }

    public void displayOrderNotRemoved() {
        io.print("Order was not removed.");
    }

    public void displayOrderNotSubmitted() {
        io.print("Order has not been submitted.");
    }
    
    public void displayBackupResults() {
        io.print("Order backup was successful");
    }
    
    public void displayInvalidChoice() {
        io.print("Invalid input! Please re-enter one of the correct options.");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
