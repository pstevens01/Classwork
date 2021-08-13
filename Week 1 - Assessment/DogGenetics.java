/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.week1assessment;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class DogGenetics {

    /**
     * @param args the command line arguments
     */
    static Random rng = new Random();
    
    public static void main(String[] args) {
        String dogName;
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("What is your dog's name?");
        dogName = in.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here.");
        
        System.out.println(dogName + " is:\n");
        buildDogGenetics();
    }
    
    // Builds a dogs genetic profile of 5 different breeds based on 5 randomly generated numbers that sum to 100
     public static void buildDogGenetics() {
        int firstBreed = rng.nextInt((100) + 1);
        int secondBreed = rng.nextInt((100 - firstBreed) + 1);
        int thirdBreed = rng.nextInt((100 - firstBreed - secondBreed) + 1);
        int fourthBreed = rng.nextInt((100 - firstBreed - secondBreed - thirdBreed) + 1);
        int fifthBreed = 100 - firstBreed - secondBreed - thirdBreed - fourthBreed;
        
        System.out.println(firstBreed + "% Ibizan Hound");
        System.out.println(secondBreed + "% Irish Wolfhound");
        System.out.println(thirdBreed + "% Boxer");
        System.out.println(fourthBreed + "% Xoloitzcuintli");
        System.out.println(fifthBreed + "% Bull Terrier\n");
        System.out.println("Wow, that's QUITE the dog!");
     }
}
