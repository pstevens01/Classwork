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
public class StringsExerciseDTest {
    
    public StringsExerciseDTest() {
    }

    /**
     * Test of simpleReverse method, of class StringsExerciseD.
     */
    @Test
    public void testSimpleReverseFunTimes() {
        String phrase = "fun times";
        String expResult = "semit nuf";
        String result = StringsExerciseD.simpleReverse(phrase);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSimpleReverseLlamaDuck() {
        String phrase = "llama llama duck";
        String expResult = "kcud amall amall";
        String result = StringsExerciseD.simpleReverse(phrase);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSimpleReverseHannah() {
        String phrase = "hannah";
        String expResult = "hannah";
        String result = StringsExerciseD.simpleReverse(phrase);
        assertEquals(expResult, result);
    }
    
}
