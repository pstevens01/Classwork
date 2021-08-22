/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.fileio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class StateCapitalsApp {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Capital> states = new HashMap<>();
        
        Scanner userInput = new Scanner(System.in);
        Scanner in = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Paul\\Desktop\\Mthree Java Training Course\\"
                + "ClassesAndObjects\\src\\main\\java\\com\\sg\\classesandobjects\\fileio\\MoreStateCapitals.txt")));
        Capital tempCapital;
        String currentLine;
        String[] state;
        int count = 0;
        
        while (in.hasNextLine()) {
            currentLine = in.nextLine();
            state = currentLine.split("::");
            states.put(state[0], new Capital(state[1], state[2], state[3]));
            count++;
        }
        System.out.println(count + " STATE/CAPITAL PAIRS LOADED.");

        for (String k : states.keySet()) {
            tempCapital = (Capital)states.get(k);
            System.out.println(k + " - " + tempCapital.getName() + " | Pop: " + tempCapital.getPopulation() + " | "
                    + "Area: " + tempCapital.getSqMiles() + " sq mi");
        }
        
        System.out.println("Please enter the lower limit for capital city population:\n");
        int minPop = Integer.parseInt(userInput.nextLine());
        
        System.out.println("LISTING CAPITALS WITH POPULATIONS GREATER THAN: " + minPop + "\n");
        for (String j : states.keySet()) {
            tempCapital = (Capital)states.get(j);
            if (tempCapital.getPopulation() > minPop) {
                System.out.println(j + " - " + tempCapital.getName() + " | Pop: " + tempCapital.getPopulation() + " | "
                    + "Area: " + tempCapital.getSqMiles() + " sq mi");
            }
        }
        
        System.out.println("Please enter the upper limit for capital city sq mileage:\n");
        int maxSqMiles = Integer.parseInt(userInput.nextLine());
        
        System.out.println("LISTING CAPITALS WITH AREAS LESS THAN: " + maxSqMiles + "\n");
        for (String j : states.keySet()) {
            tempCapital = (Capital)states.get(j);
            if (tempCapital.getSqMiles() < maxSqMiles) {
                System.out.println(j + " - " + tempCapital.getName() + " | Pop: " + tempCapital.getPopulation() + " | "
                    + "Area: " + tempCapital.getSqMiles() + " sq mi");
            }
        }
    }
}
