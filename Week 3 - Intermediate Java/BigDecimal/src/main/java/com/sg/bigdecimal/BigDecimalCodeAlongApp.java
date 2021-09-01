/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bigdecimal;

import java.math.BigDecimal;

/**
 *
 * @author Paul
 */
public class BigDecimalCodeAlongApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BigDecimalMath myMath = new BigDecimalMath();
        
        BigDecimal op1 = new BigDecimal("10");
        BigDecimal op2 = new BigDecimal("3");
        
        System.out.println(myMath.calculate(MathOperators.PLUS, op1, op2));
        System.out.println(myMath.calculate(MathOperators.MINUS, op1, op2));
        System.out.println(myMath.calculate(MathOperators.MULTIPLY, op1, op2));
        System.out.println(myMath.calculate(MathOperators.DIVIDE, op1, op2));
    }
    
}
