/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.whiles;

import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class StayPositive {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number; 
        int count = 0;
        
        System.out.println("What number would you like to count down from?");
        number = in.nextInt();
        
        while (number >= 0) {
            if (count > 9) {
                System.out.println("");
                count = 0;
            } else {
                System.out.print(number + " ");
                number--;
                count++;
            }
            
        }
    }
}
