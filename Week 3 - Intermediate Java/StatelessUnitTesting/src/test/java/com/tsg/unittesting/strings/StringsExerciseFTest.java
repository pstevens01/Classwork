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
public class StringsExerciseFTest {
    
    public StringsExerciseFTest() {
    }

    /**
     * Test of longestWord method, of class StringsExerciseF.
     */
    @Test
    public void testLongestWordOne() {
        String aPhrase = "Invention my dear friends is 93% perspiration 6% electricity 4% evaporation and 2% butterscotch ripple";
        String expResult = "perspiration";
        String result = StringsExerciseF.longestWord(aPhrase);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLongestWordTwo() {
        String aPhrase = "All well-established principles should be periodically challenged";
        String expResult = "well-established";
        String result = StringsExerciseF.longestWord(aPhrase);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLongestWordThree() {
        String aPhrase = "Never argue with the data";
        String expResult = "Never";
        String result = StringsExerciseF.longestWord(aPhrase);
        assertEquals(expResult, result);
    }
    
}
