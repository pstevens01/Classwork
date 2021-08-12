/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foundations.flowcontrol.arrays;

/**
 *
 * @author Paul
 */
public class SimpleCombination {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 35, 45, 47, 49}; // 12 numbers
        int[] secondHalf = {51, 54, 68, 71, 75, 78, 82, 84, 85, 89, 91, 100}; // also 12!
    
        int[] wholeNumbers = new int[firstHalf.length + secondHalf.length];
        wholeNumbers = combineIntArrays(firstHalf, secondHalf);
        
        printArray(wholeNumbers);
    }
    
    public static int [] combineIntArrays(int[] a, int [] b) {
        int aLength = a.length;
        int bLength = b.length;
        
        int[] allNumbers = new int[aLength + bLength];
        
        for (int i = 0; i < allNumbers.length; i++) {
            if (i < aLength) {
                allNumbers[i] = a[i];
            } else {
                allNumbers[i] = b[i - 12];
            }
        }
        return allNumbers;
    }
    
    public static void printArray(int [] arr) {
        System.out.println("All together now!:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
