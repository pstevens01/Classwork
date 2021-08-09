/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practice;

import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class Practice {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name;
        System.out.println("Please enter your name: ");
        name = in.nextLine();
        System.out.println("Your name is " + name);
    }
    
}
