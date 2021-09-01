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
public class LogicExerciseBTest {
    
    public LogicExerciseBTest() {
    }

    /**
     * Test of placeOf method, of class LogicExerciseB.
     */
    @Test
    public void testPlaceOfFirst() {
        int place = 1;
        LogicExerciseB instance = new LogicExerciseB();
        String expResult = "1st";
        String result = instance.placeOf(place);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPlaceOfThird() {
        int place = 3;
        LogicExerciseB instance = new LogicExerciseB();
        String expResult = "3rd";
        String result = instance.placeOf(place);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPlaceOfTwentySecond() {
        int place = 22;
        LogicExerciseB instance = new LogicExerciseB();
        String expResult = "22nd";
        String result = instance.placeOf(place);
        assertEquals(expResult, result);
    }
}
