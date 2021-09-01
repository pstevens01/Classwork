/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.enums;

import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class DaysUntilFridayApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a day of the week:");
        DaysOfTheWeek day = DaysOfTheWeek.valueOf(in.nextLine().toUpperCase());
        
        switch(day) {
            case MONDAY:
                System.out.println("Four more days until Friday...");
                break;
            case TUESDAY:
                System.out.println("Three more days until Friday...");
                break;
            case WEDNESDAY:
                System.out.println("Two more days until Friday...");
                break;
            case THURSDAY:
                System.out.println("ONE MORE DAY UNTIL FRIDAY!");
                break;
            case FRIDAY:
                System.out.println("IT'S FRIDAY!!!");
                break;
            case SATURDAY:
                System.out.println("Six more days until Friday...");
                break;
            case SUNDAY:
                System.out.println("Five more days until Friday...");
                break;
        }
    }
    
}
