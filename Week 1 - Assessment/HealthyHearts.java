/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.week1assessment;

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
        age = Integer.parseInt(in.nextLine());
        maxHeartRate(age);
        targetHeartRateZone(age);
    }
    
    // Calculates and prints maximum heart rate based on age parameter
     public static void maxHeartRate(int age) {
         System.out.println("Your maximum heart rate should be " + (220 - age) + " beats per minute.");
     }
     
     // Calculates and prints target heart rate based on age parameter
     public static void targetHeartRateZone(int age) {
         System.out.println("Your target heart rate zone is " + (int)((220 - age) * 0.5) + " - " + 
                 (int)((220 - age) * 0.85) + " beats per minute.");
     }
}
