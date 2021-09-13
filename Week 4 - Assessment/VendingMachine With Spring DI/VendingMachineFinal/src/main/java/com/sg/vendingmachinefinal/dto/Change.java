/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinefinal.dto;

import java.math.BigDecimal;

/**
 *
 * @author Paul
 */

public class Change {
    // Used to hold each number of coins for change
    private int quarters;
    private int dimes;
    private int nickles;
    private int pennies;
    
    private BigDecimal convert = new BigDecimal("100"); // Used to convert enum value to decimal
    private BigDecimal coinPouch[]; // Used to hold coin and remainder 
    
    public Change() {
    }
    
    public void getChange(BigDecimal itemPrice){
        BigDecimal change = itemPrice;

        
        BigDecimal quarter = new BigDecimal(Coins.QUARTER.getCents());
        quarter = quarter.divide(convert); // 25 to .25
        // divideAndRemainder returns a 2 elements array the first element is the number
        // of quarters for change. The 2nd element is the remainder of change to be given
        coinPouch = change.divideAndRemainder(quarter);
        this.quarters = coinPouch[0].intValueExact();
        change = coinPouch[1];
        
        // Dimes
        BigDecimal dime = new BigDecimal(Coins.DIME.getCents());
        dime = dime.divide(convert); // 10 to .10
        coinPouch = change.divideAndRemainder(dime);
        this.dimes = coinPouch[0].intValueExact();
        change = coinPouch[1];
        
        // Nickles
        BigDecimal nickel = new BigDecimal(Coins.NICKEL.getCents());
        nickel = nickel.divide(convert); // 5 to .05
        coinPouch = change.divideAndRemainder(nickel);
        this.nickles = coinPouch[0].intValueExact();
        change = coinPouch[1];
        
        //Convert Pennies
        BigDecimal penny = new BigDecimal(Coins.PENNY.getCents());
        penny  = penny .divide(convert); // 1 to .01
        coinPouch = change.divideAndRemainder(penny);
        this.pennies = coinPouch[0].intValueExact(); 
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickles() {
        return nickles;
    }

    public int getPennies() {
        return pennies;
    }
    
    
}
