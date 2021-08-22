/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.refactor;

import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class SimpleCalculatorApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SimpleCalculator calculate = new SimpleCalculator();
        Scanner in = new Scanner(System.in);
        
        int op1 = 0;
        int op2 = 0;
        int functionChoice;
        boolean repeat = true;
        
        while (repeat == true) {
            System.out.println("<><><> Simple Calculator ><><>");
            System.out.println("1 - Add\n2 - Subtract\n3 - Multiply\n4 - Divide"
                    + "\n5 - Exit");
            
            while (true) {
                try {
                    System.out.println("Please make a seleection to perform an operation:");
                    functionChoice = Integer.parseInt(in.nextLine());
                    if (functionChoice >= 1 && functionChoice <= 5) {
                        break;
                    } else {
                        System.out.println("Selection out of range. Try again.\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Selection was not an integer. Try again.\n");
                }
            }
            
            if (functionChoice != 5) {
                while (true) {
                    try {
                        System.out.println("Please enter operand 1:");
                        op1 = Integer.parseInt(in.nextLine());
                        System.out.println("Please enter operand 2:");
                        op2 = Integer.parseInt(in.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Operand was not an integer. Try again\n");
                    }
                }
            }
            
            switch (functionChoice) {
                case 1:
                    System.out.println(op1 + " plus " + op2 + " = " + calculate.add(op1, op2) + "\n");
                    break;
                case 2:
                    System.out.println(op1 + " minus " + op2 + " = " + calculate.subtract(op1, op2) + "\n");
                    break;
                case 3:
                    System.out.println(op1 + " time " + op2 + " = " + calculate.multiply(op1, op2) + "\n");
                    break;
                case 4:
                    System.out.println(op1 + " divided by " + op2 + " = " + calculate.divide(op1, op2) + "\n");
                    break;
                case 5:
                    repeat = false;
                    break;
            }
        }
        System.out.println("Thank you for using this simple calculator.");
    }
    
}
