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
public class AllTheTrivia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String q1, q2, q3, q4;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("How many continents are there in the world?");
        q1 = in.nextLine();
        System.out.println("Which river flows through London?");
        q2 = in.nextLine();
        System.out.println("What is the name of the toy cowboy in the movie "
                + "Toy Story?");
        q3 = in.nextLine();
        System.out.println("What is the name of the type of weapon used by "
                + "Jedi Knights?");
        q4 = in.nextLine();
        
        System.out.println("If you count them on a map, there are a total of " + 
                q4 + " continents.");
        System.out.println("The river " + q3 + " flows through London!");
        System.out.println("The name of the toy cowboy in the movie Toy Story "
                + "is " + q1 + "!");
        System.out.println("Jedi Knight use the " + q2 + " as their weapon!");
    }
    
}
