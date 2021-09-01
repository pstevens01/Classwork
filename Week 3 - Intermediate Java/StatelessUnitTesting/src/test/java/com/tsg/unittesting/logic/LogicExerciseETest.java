/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.unittesting.logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul
 */
public class LogicExerciseETest {
    
    public LogicExerciseETest() {
    }

    /**
     * Test of whatColor method, of class LogicExerciseE.
     */
    @Test
    public void testWhatColorYellow() {

        int waveLengthNM = 575;
        int frequencyTHZ = 510;
        double photonicEnergyEV = 2.15;
        String expResult = "Yellow";
        String result = LogicExerciseE.whatColor(waveLengthNM, frequencyTHZ, photonicEnergyEV);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testWhatColorViolet() {

        int waveLengthNM = 449;
        int frequencyTHZ = 670;
        double photonicEnergyEV = 3.00;
        String expResult = "Violet";
        String result = LogicExerciseE.whatColor(waveLengthNM, frequencyTHZ, photonicEnergyEV);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testWhatColorUnknown() {

        int waveLengthNM = 621;
        int frequencyTHZ = 475;
        double photonicEnergyEV = 16.5;
        String expResult = "Unknown";
        String result = LogicExerciseE.whatColor(waveLengthNM, frequencyTHZ, photonicEnergyEV);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testWhatColorOrange() {

        int waveLengthNM = 590;
        int frequencyTHZ = 508;
        double photonicEnergyEV = 2.05;
        String expResult = "Orange";
        String result = LogicExerciseE.whatColor(waveLengthNM, frequencyTHZ, photonicEnergyEV);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testWhatColorYellow_Green() {

        int waveLengthNM = 570;
        int frequencyTHZ = 526;
        double photonicEnergyEV = 2.17;
        String expResult = "Yellow-Green";
        String result = LogicExerciseE.whatColor(waveLengthNM, frequencyTHZ, photonicEnergyEV);
        assertEquals(expResult, result);
    }
}
