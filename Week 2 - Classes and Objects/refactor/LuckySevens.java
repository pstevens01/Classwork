/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classesandobjects.refactor;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class LuckySevens {
    
    public void startGame() {
        int amount, dice1, dice2;
        int rolls = 0;
        int maxMoney = 0;
        int maxRolls = 0;
        
        
        Scanner in = new Scanner(System.in);
        Random dice = new Random();
        
        System.out.println("How much money do you have to play with?");
        amount = Integer.parseInt(in.nextLine());
        
        while (amount > 0) {
            rolls++;
            dice1 = dice.nextInt(6) + 1;
            dice2 = dice.nextInt(6) + 1;
            
            if (dice1 + dice2 == 7) {
                amount += 4;
            } else {
                amount -= 1;
            }
            
            if (amount > maxMoney) {
                maxMoney = amount;
                maxRolls = rolls;
            }
        }
        System.out.println("You are broke after " + rolls + " rolls.");
        System.out.println("You should have quit after " + maxRolls + " rolls when you had $" + maxMoney + ".");
    }
}
