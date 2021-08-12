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
public class MiniMadLibs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String noun1, noun2, adj1, adj2, pluralNoun1, pluralNoun2, pluralNoun3,
                verbPresent, verbPast, num;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Let's play MAD LIBS!\n");
        
        System.out.println("Please enter a noun:"); 
        noun1 = in.nextLine();
        
        System.out.println("Please give me an adjective:");
        adj1 = in.nextLine();
        
        System.out.println("And now another noun:");
        noun2 = in.nextLine();
        
        System.out.println("Now I need a number:");
        num = in.nextLine();
        
        System.out.println("One more adjective:");
        adj2 = in.nextLine();
        
        System.out.println("Give me a plural noun:");
        pluralNoun1 = in.nextLine();
        
        System.out.println("A second plural noun:");
        pluralNoun2 = in.nextLine();
        
        System.out.println("And a final plural noun:");
        pluralNoun3 = in.nextLine();
        
        System.out.println("Enter a verb in the present tense:");
        verbPresent = in.nextLine();
        
        System.out.println("And the same verb in the past tense:");
        verbPast = in.nextLine();
        
        System.out.println("*** NOW LETS GET MAD (libs) ***");
        System.out.println(noun1 + ": the " + adj1 + " frontier. There are the voyages of the starship " + noun2 + ". Its " + num + 
                "-year mission: to explore strange " + adj2 + " " + pluralNoun1 + ", to seek out " + adj2 + " " + pluralNoun2 + " and " + 
                adj2 + " " + pluralNoun3 + ", to bodly " + verbPresent + " where no one has " + verbPast + " before.");
    }
    
}
