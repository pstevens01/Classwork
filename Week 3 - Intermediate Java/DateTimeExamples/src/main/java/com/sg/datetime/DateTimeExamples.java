/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Paul
 */
public class DateTimeExamples {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Gets todays date
        LocalDate ld = LocalDate.now();
        System.out.println(ld);
        
        // Passes LocalDate obj specified date
        ld = LocalDate.parse("1999-12-31");
        System.out.println(ld);
        
        // Using formatter method to change how the date is dipslayed
        String formatted = ld.format(DateTimeFormatter.ofPattern("MM/dd/yyy"));
        System.out.println(formatted);
        
        // Display date using LocalizedDate formatting style 'short, medium, long, full'
        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        // Subtract days from date
        LocalDate past = ld.minusDays(3);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        // Subtract months from date
        past = ld.minusMonths(3);
        formatted = past.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(formatted);
        
        // Calculate difference in dates
        Period diff = ld.until(past);
        System.out.println(diff);
        System.out.println(diff.getMonths()); // Dates in the past will be negative
        diff = past.until(ld);
        System.out.println(diff.getMonths()); // Dates in the future will be positive
        
        // Local Date Time obj includes full timestamp (date/time)
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        formatted = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        System.out.println(formatted);
        
        // Working with Date and Gregorian Calendar objs and conversions for LocalDate
        Date legacyDate = new Date();
        System.out.println(legacyDate);
        
        GregorianCalendar legacyCalendar = new GregorianCalendar();
        System.out.println(legacyCalendar);
        
        // Converting Date to LocalDateTime
        ZonedDateTime zdt = ZonedDateTime.ofInstant(legacyDate.toInstant(), ZoneId.systemDefault());
        ld = zdt.toLocalDate();
        System.out.println(ld);
        
        zdt = legacyCalendar.toZonedDateTime();
        ld = zdt.toLocalDate();
        System.out.println(ld);
    }
    
}
