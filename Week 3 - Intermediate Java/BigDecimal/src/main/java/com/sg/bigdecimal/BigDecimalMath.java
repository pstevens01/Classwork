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
public class BigDecimalMath {
    public BigDecimal calculate(
            MathOperators operator, BigDecimal op1,BigDecimal op2) {
        switch (operator) {
            case PLUS:
                return op1.add(op2);
            case MINUS:
                return op1.subtract(op2);
            case MULTIPLY:
                return op1.multiply(op2);
            case DIVIDE:
                return op1.divide(op2, 2, RoundingMode.HALF_UP);
            default:
                throw new UnsupportedOperationException("Unknown Math Operator");
        }
    }
}
