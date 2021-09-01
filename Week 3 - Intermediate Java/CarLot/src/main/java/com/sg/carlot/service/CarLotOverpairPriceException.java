/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carlot.service;

/**
 *
 * @author Paul
 */
public class CarLotOverpairPriceException extends Exception {
    public CarLotOverpairPriceException(String message) {
        super(message);
    }
    
    public CarLotOverpairPriceException(String message, Throwable cause) {
        super(message, cause);
    }
}
