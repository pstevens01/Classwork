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
public class DoItBetter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int miles, hotdogs, languages;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("How many miles are you able to run?");
        miles = in.nextInt();
        System.out.println("Wow! You're only able to run " + miles + " miles? "
                + "I'm able to run " + (miles * 2 + 1) + "!");
        
        System.out.println("What is the number of hot dogs you can eat in one "
                + "sitting?");
        hotdogs = in.nextInt();
        System.out.println(hotdogs + " is a weak number of hot dogs. I can gobble "
                + "down " + (hotdogs * 2 + 1) + " hot dogs in under a minute!");
        
        System.out.println("How many language(s) have you mastered?");
        languages = in.nextInt();
        System.out.println("Only " + languages + " language(s)? I can recite "
                + "Donte's Inferno in " + (languages * 2 + 1) + " languages.");
    }
    
}
