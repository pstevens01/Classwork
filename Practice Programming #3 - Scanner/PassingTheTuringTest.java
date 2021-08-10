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
public class PassingTheTuringTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String name, color, food;
        int num;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Hello there! \nWhat's your name? ");
        name = in.nextLine();
        System.out.println("Hi, " + name + "! I'm Paul.");
        
        System.out.println("What's your favorite color?");
        color = in.nextLine();
        System.out.println("Huh, " + color + "? Mine's Burnt Orange.\n");
        
        System.out.println("I really like limes. They're my favorite fruit, too.");
        System.out.println("What's YOUR favorite fruit, " + name + "?");
        food = in.nextLine();
        
        System.out.println("Really? " + food + "? That's wild!");
        System.out.println("Speaking of favorites, what's your favorite number?");
        num = in.nextInt();
        
        System.out.println(num + " is a cool number. Mine's 14.");
        System.out.println("Did you know " + num + " * 14 is " + (num * 14) + "? "
                + "That's a cool number too!\n");
        
        System.out.println("Well, thanks for talking to me, " + name + "!");
    }
    
}
