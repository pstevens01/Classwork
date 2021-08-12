/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.random;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class HighRoller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random dice = new Random();
        String key; 
        int count = 1, roll;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Press any key to roll the dice.");
        key = in.nextLine();
        
        while (key != null) {
            roll = dice.nextInt(6) + 1;
            System.out.println("You rolled a " + roll + ".");
            if (roll == 1 || roll == 6) {
                System.out.println(roll + " is a winning number. You Win!");
                System.out.println("It took you " + count + " roll(s).");
                break;
            } else {
                System.out.println(roll + " is not a winning number try again.");
                count++;
                System.out.println("Please press any key.");
                key = in.nextLine();
            }
        }
    }
    
}
