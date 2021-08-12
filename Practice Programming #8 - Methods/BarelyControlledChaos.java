/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.methods;

import java.util.Random;

/**
 *
 * @author Paul
 */
public class BarelyControlledChaos {

    /**
     * @param args the command line arguments
     */
    static Random rng = new Random();
    
    public static void main(String[] args) {
        
        String color = randomColor(); // call color method here
        String animal = randomAnimal(); // call animal method again here
        String colorAgain = randomColor(); // call color method again here
        int weight = randomNumber(5, 200); // call number method,
            // with a range between 5 - 200
        int distance = randomNumber(10, 20); // call number method,
            // with a range between 10 - 20
        int number = randomNumber(10000, 20000); // call number method,
            // with a range between 10000 - 20000
        int time = randomNumber(2, 6); // call number method,
            // with a range between 2 - 6
            
            System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
            + weight + "lb " + " miniature " + animal
            + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over "
            + number + " " + colorAgain + " poppies for nearly "
            + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, "
            + "let me tell you!");
    }
    
    public static String randomColor() {
        String[] colors = {"Red", "Blue", "Green", "Yellow", "Purple"};
        String chosenColor = colors[rng.nextInt(5)];
        return chosenColor;
    }
    
    public static String randomAnimal() {
        String[] animals = {"Dog", "Cat", "Fish", "Rat", "Bird"};
        String chosenAnimal = animals[rng.nextInt(5)];
        return chosenAnimal;
    }
    
    public static int randomNumber(int a, int b) {
        int chosenNumber = rng.nextInt(b - a) + a;
        return chosenNumber;
    }
}
