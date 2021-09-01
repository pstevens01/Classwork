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
public class StringsExerciseBTest {
    
    public StringsExerciseBTest() {
    }

    /**
     * Test of tripleIt method, of class StringsExerciseB.
     */
    @Test
    public void testTripleItLlama() {
        String theString = "Llama";
        String expResult = "llamaLLAMAllama";
        String result = StringsExerciseB.tripleIt(theString);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTripleItHa() {
        String theString = "ha";
        String expResult = "haHAha";
        String result = StringsExerciseB.tripleIt(theString);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTripleItBeetleJuice() {
        String theString = "Beetlejuice";
        String expResult = "beetlejuiceBEETLEJUICEbeetlejuice";
        String result = StringsExerciseB.tripleIt(theString);
        assertEquals(expResult, result);
    }
}
