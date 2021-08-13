/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.week1assessment;

/**
 *
 * @author Paul
 */
public class SummativeSums {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] num1 = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int[] num2 = {999, -60, -77, 14, 160, 301};
        int[] num3 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99};
        
        System.out.println("Array #1 sum: " + sumArrayElements(num1));
        System.out.println("Array #2 sum: " + sumArrayElements(num2));
        System.out.println("Array #3 sum: " + sumArrayElements(num3));
    }
    
    // Sums all elements of an integer array that was passed as a parameter
    public static int sumArrayElements(int[] arr) {
        int sum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
