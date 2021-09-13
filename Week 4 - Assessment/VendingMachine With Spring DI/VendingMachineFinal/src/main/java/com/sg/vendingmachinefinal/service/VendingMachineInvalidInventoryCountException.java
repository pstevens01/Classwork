/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinefinal.service;

/**
 *
 * @author Paul
 */
public class VendingMachineInvalidInventoryCountException extends Exception {
    
    public VendingMachineInvalidInventoryCountException(String message) {
        super(message);
    }
    
    public VendingMachineInvalidInventoryCountException(String message, Throwable cause) {
        super(message, cause);
    }
}
