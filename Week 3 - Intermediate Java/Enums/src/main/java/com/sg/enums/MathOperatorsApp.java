/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.enums;

import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class MathOperatorsApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int op1, op2;
        Scanner in = new Scanner(System.in);
        
        System.out.println("Input first operand:");
        op1 = in.nextInt();
        System.out.println("Input second operand:");
        op2 = in.nextInt();
        
        int sum = calculate(MathOperators.PLUS, op1, op2);
        int difference = calculate(MathOperators.MINUS, op1, op2);
        int product = calculate(MathOperators.MULTIPLY, op1, op2);
        int quotient = calculate(MathOperators.DIVIDE, op1, op2);
        
        System.out.println(op1 + " + " + op2 + " = " + sum);
        System.out.println(op1 + " - " + op2 + " = " + difference);
        System.out.println(op1 + " * " + op2 + " = " + product);
        System.out.println(op1 + " / " + op2 + " = " + quotient);
    }
    
    public static int calculate(MathOperators operator, int op1, int op2) {
        switch (operator) {
            case PLUS:
                return op1 + op2;
            case MINUS:
                return op1 - op2;
            case MULTIPLY:
                return op1 * op2;
            case DIVIDE:
                return op1 / op2;
            default:
                throw new UnsupportedOperationException();
        }
    }
    
}
