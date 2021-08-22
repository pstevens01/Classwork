/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.userio;

import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class UserIOImpl implements UserIO {

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);
        String userInput = in.nextLine();
        
        return userInput;
    }

    @Override
    public int readInt(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);
        int userInput = Integer.parseInt(in.nextLine());
        
        return userInput;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        Scanner in = new Scanner(System.in);
        int userInput;
        
        do {
            System.out.println(prompt);
            userInput = Integer.parseInt(in.nextLine());
            if (userInput > min && userInput < max) {
                break;
            }
        } while (true);
        
        return userInput;
    }

    @Override
    public double readDouble(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);
        double userInput = Double.parseDouble(in.nextLine());
        
        return userInput;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        Scanner in = new Scanner(System.in);
        double userInput;
        
        do {
            System.out.println(prompt);
            userInput = Double.parseDouble(in.nextLine());
            if (userInput > min && userInput < max) {
                break;
            }
        } while (true);
        
        return userInput;
    }

    @Override
    public float readFloat(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);
        float userInput = Float.parseFloat(in.nextLine());
        
        return userInput;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        Scanner in = new Scanner(System.in);
        float userInput;
        
        do {
            System.out.println(prompt);
            userInput = Float.parseFloat(in.nextLine());
            if (userInput > min && userInput < max) {
                break;
            }
        } while (true);
        
        return userInput;
    }

    @Override
    public long readLong(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);
        long userInput = Long.parseLong(in.nextLine());
        
        return userInput;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        Scanner in = new Scanner(System.in);
        long userInput;
        
        do {
            System.out.println(prompt);
            userInput = Long.parseLong(in.nextLine());
            if (userInput > min && userInput < max) {
                break;
            }
        } while (true);
        
        return userInput;
    }
    
}
