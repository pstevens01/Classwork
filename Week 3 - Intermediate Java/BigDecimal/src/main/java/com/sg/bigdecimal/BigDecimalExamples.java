/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Paul
 */
public class BigDecimalExamples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("42.35");
        BigDecimal b = a.setScale(1, RoundingMode.HALF_UP); // round up to 1 decimal place
        System.out.println(b.toString());
        
        b = a.setScale(1, RoundingMode.HALF_DOWN); // round down to 1 decimal place
        System.out.println(b.toString());
        
        // Division with various rounding modes
        BigDecimal op1 = new BigDecimal("10");
        BigDecimal op2 = new BigDecimal("6");
        
        BigDecimal c = op1.divide(op2, RoundingMode.HALF_UP); // Rounding up to nearing whole number
        System.out.println(c.toString());
        
        c = op1.divide(op2, 2, RoundingMode.HALF_UP); // Rounding up to 2 decimal places
        System.out.println(c.toString());
        
        c = op1.divide(op2, 2, RoundingMode.DOWN); // Rounding down to 2 decimal places
        System.out.println(c.toString());
    }
    
}
