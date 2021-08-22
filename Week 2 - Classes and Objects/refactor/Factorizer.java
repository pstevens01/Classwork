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
public class Factorizer {
    
    public void factorize() {
        Scanner in = new Scanner(System.in);
        int number; 
        int factors = 0;
        int sum = 0;
        
        System.out.println("What number would you like to factor?");
        number = Integer.parseInt(in.nextLine());
        System.out.println("The factors of " + number + " are:");
        
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                System.out.print(i + " ");
                factors++;
                sum += i;
            }
        }
        System.out.println("\n" + number + " has " + (factors + 1) + " factors.");
        
        if (sum == number) {
            System.out.println(number + " is a perfect number.");
        } else {
            System.out.println(number + " is not a perfect number.");
        }
        if (factors <= 2) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is not a prime number.");
        }
    }
}
