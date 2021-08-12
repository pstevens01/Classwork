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
public class FruitSalad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] fruit = {"Kiwi Fruit", "Gala Apple", "Granny Smith Apple", "Cherry Tomato", "Gooseberry", "Beefsteak Tomato", 
        "Braeburn Apple", "Blueberry", "Strawberry", "Navel Orange", "Pink Pearl Apple",  "Raspberry", "Blood Orange", "Sungold Tomato", 
        "Fuji Apple", "Blackberry", "Banana", "Pineapple", "Florida Orange", "Kiku Apple", "Mango", "Satsuma Orange", "Watermelon", "Snozzberry"};
    
        String[] fruitSalad = new String[12];
        int insert = 0;
        int apples = 0;
        int oranges = 0;
        int berries = 0;
        
        for (int i = 0; i < fruit.length; i++) {
            if (fruitSalad[fruitSalad.length - 1] != null) {
                break;
            } else if (fruit[i].endsWith("berry")) {
                fruitSalad[insert] = fruit[i];
                berries++;
                insert++;
            } else if (fruit[i].endsWith("Apple")) {
                if (apples < 3) {
                    fruitSalad[insert] = fruit[i];
                    apples++;
                    insert++;
                }
            } else if (fruit[i].endsWith("Orange")) {
                if (oranges < 2) {
                    fruitSalad[insert] = fruit[i];
                    oranges++;
                    insert++;
                }
            }
        }
        System.out.format("There are a total of %d different types of fruit in the baseket.\n"
                + "%d of them are Berries.\n%d of them are Apples.\nAnd %d of them are Oranges.", insert, berries, apples, oranges);
    }
}