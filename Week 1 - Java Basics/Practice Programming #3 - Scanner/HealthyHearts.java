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
public class HealthyHearts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int age;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("What is your age?");
        age = in.nextInt();
        
        System.out.println("Your maximum heart rate should be " + (220 - age) + 
                " beats per minute.");
        System.out.println("Your target HR Zone is " + (int)((220 - age) * 0.5) + 
                " - " + (int)((220 - age) * 0.85) + " beats per minute."); 
    }
    
}
