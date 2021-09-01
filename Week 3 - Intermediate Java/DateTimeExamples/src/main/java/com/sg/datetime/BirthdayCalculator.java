/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class BirthdayCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String input;
        LocalDate birthday;
        LocalDate nextDay;
        boolean thisYear = true;
        LocalDate today = LocalDate.now();
        String todayDate = today.format(format);
        
        // Parse birthday from input into LocalDate obj
        System.out.println("What's your birthday?");
        input = in.nextLine();
        
        // Print out what day of the week you were born on
        birthday = LocalDate.parse(input, format);
        System.out.println("That means you were born on a " + birthday.getDayOfWeek() + "!");
        
        // Check to see if your bithday has already happened
        nextDay = birthday.withYear(today.getYear()); // Append your birth year with this year
        if (nextDay.isBefore(today)) {
            nextDay = nextDay.plusYears(1);
            thisYear = false;
        }
        // Calculate how many more days until your birthday
        Period nextBirthday = today.until(nextDay);
        // Calculate how old you will be on your next birthday
        Period nextAge = birthday.until(nextDay);
        
        // Print out which day of the week your birthday falls on this year
        if (thisYear) {
            System.out.println("This year it falls on a " + nextDay.getDayOfWeek());
        } else {
            System.out.println("Next year it falls on a " + nextDay.getDayOfWeek());
        }
        
        // Print out how many more months until birthday and how old you will be turning
        System.out.println("And since today is " + todayDate + ", there is only " + 
                nextBirthday.getMonths() + " more months until the next one!");
        System.out.println("Bet yer excited to be turning " + nextAge.getYears() + "!");
    }
    
}
