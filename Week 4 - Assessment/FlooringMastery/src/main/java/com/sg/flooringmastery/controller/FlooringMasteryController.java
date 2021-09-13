/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.model.Order;
import com.sg.flooringmastery.service.FlooringMasteryInvalidInputException;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.view.FlooringMasteryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul
 */
@Component
public class FlooringMasteryController {
    private final FlooringMasteryServiceLayer service;
    private final FlooringMasteryView view;

    @Autowired
    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        try {
            service.importAllData();
            
            boolean keepGoing = true;
            while (keepGoing) {
                int choice = view.printMenuAndGetSelection();
                switch (choice) {
                    case 1:
                        displayOrdersByDate();
                        break;
                    case 2:
                        createOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        exportBackupData();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        view.displayInvalidChoice();
                }
            }
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage()); 
        }
    }
    
    private void displayOrdersByDate() {
        try {
            view.displayOrders(service.getOrdersByDate(view.getOrderDate()));
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (FlooringMasteryInvalidInputException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void createOrder() {
        try {
            String date = view.getOrderDate();
            String name = view.getCustomerName();
            String state = view.getState();
            String product = view.getListProductType(service.getProducts());
            String area = view.getArea();

            Order order = service.createOrder(date, name, state, product, area);
            view.displayOrderSummaryBanner();
            view.displayOrder(order);
            
            boolean confirm;
            
            do {
                String submit = view.displayOrderConfirmation();
                switch (submit) {
                    case "Y":
                        service.submitOrder(order);
                        view.displayOrderCreationSuccess();
                        confirm = false;
                        break;
                    case "N":
                        view.displayOrderNotSubmitted();
                        confirm = false;
                        break;
                    default:
                        view.displayInvalidChoice();
                        confirm = true;
                }
            } while (confirm);
                
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (FlooringMasteryInvalidInputException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void editOrder() {
        try {
            Order order = service.getOrder(view.getOrderDate(), view.getOrderNumber());
            
            view.displayEditOrderBanner();
            view.displayOrder(order);
            view.displayEditInstructions();
            
            order = service.editOrder(order, view.getCustomerName(), view.getState(), view.getListProductType(service.getProducts()), view.getArea());
            view.displayOrderSummaryBanner();
            view.displayOrder(order);
            
            boolean confirm;
            do {
                String submit = view.displayOrderConfirmation();
                switch (submit) {
                    case "Y":
                        service.changeOrder(order);
                        view.displayOrderCreationSuccess();
                        confirm = false;
                        break;
                    case "N":
                        view.displayOrderNotSubmitted();
                        confirm = false;
                        break;
                    default:
                        view.displayInvalidChoice();
                        confirm = true;
                }
            } while (confirm);
            
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (FlooringMasteryInvalidInputException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void removeOrder() {
        try {
            Order order = service.getRemoveOrder(view.getOrderDate(), view.getOrderNumber());
            view.displayOrder(order);
            
            boolean confirm;
            do {
                String submit = view.displayRemoveOrderConfirmation();
                switch (submit) {
                    case "Y":
                        service.removeOrder(order);
                        service.exportAllData();
                        view.displayRemoveOrderSuccess();
                        confirm = false;
                        break;
                    case "N":
                        view.displayOrderNotRemoved();
                        confirm = false;
                        break;
                    default:
                        view.displayInvalidChoice();
                        confirm = true;
                }
            } while (confirm);
            
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        } catch (FlooringMasteryInvalidInputException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void exportBackupData() {
        try {
            service.exportBackupData();
            view.displayBackupResults();
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
}
