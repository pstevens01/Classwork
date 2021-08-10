/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.variables;

/**
 *
 * @author Paul
 */
public class MoreBucketsMoreFun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Declaring multiple varables on the same line
        int butterflies, beetles, bugs;
        
        // Assigning values to variables
        butterflies = 5;
        beetles = 9;
        
        bugs = butterflies + beetles;
        System.out.println("There are only " + butterflies + " butterflies,");
        System.out.println("but there are " + bugs + " bugs in all.");

        System.out.println("Uh oh, my dog ate one.");
        butterflies--;
        System.out.println("Now there are only " + butterflies + " butterflies left.");
        System.out.println("But there are still " + bugs + " bugs left...");
        System.out.println("Wait a minute!");
        System.out.println("... maybe my computer can't do math, after all!");
    }
    
}
