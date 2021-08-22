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
public class InterestCalculator {
    
    public void calculate() {
        Scanner in = new Scanner(System.in);
        double amount, updatedAmount, interestEarned, rate;
        int years, method, divisor;
        
        System.out.println("How much would you like to invest?");
        amount = Double.parseDouble(in.nextLine());
        
        System.out.println("How many years for the investment?");
        years = Integer.parseInt(in.nextLine());
        
        System.out.println("What is the annual rate % growth?");
        rate = Double.parseDouble(in.nextLine());
        
        System.out.println("How would you like the interest compounded?");
        System.out.println("1) Quarterly   2) Monthly   3) Daily");
        method = Integer.parseInt(in.nextLine());
        
        switch (method) {
            case 1:
                divisor = 4;
                break;
            case 2:
                divisor = 12;
                break;
            default:
                divisor = 365;
                break;               
        }
        rate /= divisor;
        
        System.out.println("\nCalculating...");
        
        for (int i = 1; i <= years; i++) {
            updatedAmount =  amount;
            for (int j = 0; j < divisor; j++) {
                updatedAmount *= (1 +(rate / 100));
            }
            System.out.println("Year " + i + ":");
            System.out.println("Began the years with $" + amount);
            System.out.println("You earned $" + (updatedAmount - amount));
            System.out.println("Ended with $" + updatedAmount + "\n");
            amount = updatedAmount;
        }
    } 
}
