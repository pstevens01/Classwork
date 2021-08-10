/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.scanner;

import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class BiggerBetterAdder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num1, num2, num3;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("We are going to add together three numbers of your "
                + "choosing.");
        System.out.println("Number 1:");
        num1 = in.nextInt();
        System.out.println("Number 2:");
        num2 = in.nextInt();
        System.out.println("Number 3:");
        num3 = in.nextInt();
        
        System.out.println("The sum of your three numbers is: " + 
                (num1 + num2 + num3));
        
    }
    
}
