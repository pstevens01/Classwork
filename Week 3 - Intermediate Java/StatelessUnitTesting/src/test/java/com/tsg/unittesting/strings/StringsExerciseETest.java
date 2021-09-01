/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.unittesting.strings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul
 */
public class StringsExerciseETest {
    
    public StringsExerciseETest() {
    }

    /**
     * Test of containsTheOther method, of class StringsExerciseE.
     */
    @Test
    public void testContainsTheOtherOneTone() {
        String one = "one";
        String two = "tone";
        boolean expResult = true;
        boolean result = StringsExerciseE.containsTheOther(one, two);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testContainsTheOtherSameSame() {
        String one = "same";
        String two = "same";
        boolean expResult = false;
        boolean result = StringsExerciseE.containsTheOther(one, two);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testContainsTheOtherFancyPants() {
        String one = "fancypants";
        String two = "pants";
        boolean expResult = true;
        boolean result = StringsExerciseE.containsTheOther(one, two);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testContainsTheOtherLlamaDuck() {
        String one = "llama";
        String two = "duck";
        boolean expResult = false;
        boolean result = StringsExerciseE.containsTheOther(one, two);
        assertEquals(expResult, result);
    }
}
