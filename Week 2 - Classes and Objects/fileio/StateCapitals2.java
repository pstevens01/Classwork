/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.fileio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Paul
 */
public class StateCapitals2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, String> stateCapitals = new HashMap<>();
        Scanner in = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Paul\\Desktop\\Mthree Java Training Course\\"
                + "ClassesAndObjects\\src\\main\\java\\com\\sg\\classesandobjects\\fileio\\StateCapitals.txt")));
        String[] state = new String[2];
        String currentLine;
        Random rand = new Random();
        Scanner userInput = new Scanner(System.in);
        
        while (in.hasNextLine()) {
            currentLine = in.nextLine();
            state = currentLine.split("::");
            stateCapitals.put(state[0], state[1]);
        }
        
        System.out.println(stateCapitals.size() + " states and capitals are loaded.");
        System.out.println("==================================");
        
        System.out.println("Here are the states:");
        for (String k : stateCapitals.keySet()) {
            System.out.println(k);
        }
        
        List<String> keys = new ArrayList<>(stateCapitals.keySet());
        int randomIndex = rand.nextInt(keys.size());
        String randomState = keys.get(randomIndex);
        String capitalGuess = "";
        
        System.out.println("Lets see if you know your state capitals. What is the capital of '" + randomState + "'?");
        capitalGuess = userInput.nextLine();
        if (capitalGuess.equals(stateCapitals.get(randomState))) {
            System.out.println("Great! You know your state capitals!");
        } else {
            System.out.println("You got it wrong. The correct answer is " + stateCapitals.get(randomState) + "."); 
        }
        
    }
    
}
