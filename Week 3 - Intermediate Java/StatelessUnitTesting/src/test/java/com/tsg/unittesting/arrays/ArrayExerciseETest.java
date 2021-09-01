/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.unittesting.arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul
 */
public class ArrayExerciseETest {
    
    public ArrayExerciseETest() {
    }

    /**
     * Test of camelCaseIt method, of class ArrayExerciseE.
     */
    @Test
    public void testCamelCaseItLlamaDuck() {
        String[] words = {"llama", "llama", "duck"};
        String expResult = "llamaLlamaDuck";
        String result = ArrayExerciseE.camelCaseIt(words);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCamelCaseItLlamaOat() {
        String[] words = {"lambs", "eat", "oats", "and", "does", "eat", "oats"};
        String expResult = "lambsEatOatsAndDoesEatOats";
        String result = ArrayExerciseE.camelCaseIt(words);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCamelCaseItJedi() {
        String[] words = {"DO", "OR", "DO", "NOT", "THERE", "IS", "NO", "TRY"};
        String expResult = "doOrDoNotThereIsNoTry";
        String result = ArrayExerciseE.camelCaseIt(words);
        assertEquals(expResult, result);
    }
}
