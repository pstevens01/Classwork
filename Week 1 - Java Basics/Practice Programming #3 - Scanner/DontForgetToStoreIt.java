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
public class DontForgetToStoreIt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int meaningOfLifeAndEverything;
        double pi;
        String cheese, color;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Give me pi to atleast 5 decimals: ");
        pi = Double.parseDouble(input.nextLine());
        
        System.out.println("What is the meaning of life, the universe & "
                + " everything?");
        meaningOfLifeAndEverything = Integer.parseInt(input.nextLine());
        
        System.out.println("What is your favorite kind of cheese? ");
        cheese = input.nextLine();
        
        System.out.println("Do you like the color red or blue more? ");
        color = input.nextLine();
        
        System.out.println("Ooh, " + color + " " + cheese +" sounds delicious!");
        System.out.println("The circumference of life is " +( 2 * pi * meaningOfLifeAndEverything));
    }
    
}
