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
public class GuessMe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int number = 14;
        int guess;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("I've chosen a number. Betcha can't guess it!");
        guess = in.nextInt();
        
        System.out.println("Your guess: " + guess);
        
        if (guess == number) {
            System.out.println("Wow you guess the number on the first try!");
        } else if (guess < number) {
            System.out.println("Your guess of " + guess + " is too low. The numer was " + number + ".");
        } else {
            System.out.println(guess + "? Nice try but thats too high. The number was " + number  + ".");
        }
    }
    
}
