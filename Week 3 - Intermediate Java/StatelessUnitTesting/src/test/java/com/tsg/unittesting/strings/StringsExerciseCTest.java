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
public class StringsExerciseCTest {
    
    public StringsExerciseCTest() {
    }

    /**
     * Test of removeTheVowels method, of class StringsExerciseC.
     */
    @Test
    public void testRemoveTheVowelsTruncate() {
        String word = "truncate";
        String expResult = "trnct";
        String result = StringsExerciseC.removeTheVowels(word);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testRemoveTheVowelsSquashed() {
        String word = "squashed";
        String expResult = "sqshd";
        String result = StringsExerciseC.removeTheVowels(word);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testRemoveTheVowelsCompressed() {
        String word = "compressed";
        String expResult = "cmprssd";
        String result = StringsExerciseC.removeTheVowels(word);
        assertEquals(expResult, result);
    }
}
