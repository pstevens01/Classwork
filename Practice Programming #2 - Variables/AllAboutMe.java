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
public class AllAboutMe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String name, food, livingSpace;
        int pets;
        boolean whistle;
        
        name = "Paul";
        food = "tacos";
        pets = 0;
        livingSpace = "house";
        whistle = true;
        
        System.out.println("My name is " + name + ".");
        System.out.println("My favorite food is " + food + ".");
        System.out.println("I have " + pets + " pets.");
        System.out.println("I live in a " + livingSpace + " with my crazy kids!");
        System.out.println("It is " + whistle + " I know how to whistle.");
    }
    
}
