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
public class GuessMeMore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int guess;
        
        Scanner in = new Scanner(System.in);
        Random rng = new Random();
        int number = rng.nextInt(201) - 100;
        
        System.out.println("A number has been randomly chosen between -100 and 100. Try to guess it!");
        
        while (true) {
            System.out.println("Your guess: ");
            guess = in.nextInt();
            if (guess == number) {
                break;
            } else if (guess < number) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }
        System.out.println("You finally guess it! Good job!");
    }
    
}
