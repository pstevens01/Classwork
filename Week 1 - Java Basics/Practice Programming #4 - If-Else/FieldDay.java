/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class FieldDay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String lastName;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Lets figure out which team you'll be on, please enter your last name: ");
        lastName = in.nextLine();
        
        if (lastName.compareTo("Baggins") <= 0) {
            System.out.println("You are on team Red Dragons.");
        } else if (lastName.compareTo("Dresden") <= 0) {
            System.out.println("You are on team Dark Wizards.");
        } else if (lastName.compareTo("Howl") <= 0) {
            System.out.println("You are on team Moving Castles.");
        } else if (lastName.compareTo("Potter") <= 0) {
            System.out.println("You are on team Golden Snitches."); 
        } else if (lastName.compareTo("Vimes") <= 0) {
            System.out.println("You are on team Night Guards.");
        } else {
            System.out.println("You are on team Black Holes.");
        }
        System.out.println("Good luck in the game!");
    }
    
}
