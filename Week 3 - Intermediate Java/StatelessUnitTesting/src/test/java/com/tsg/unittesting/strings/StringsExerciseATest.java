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
public class StringsExerciseATest {
    
    public StringsExerciseATest() {
    }

    /**
     * Test of yell method, of class StringsExerciseA.
     */
    @Test
    public void testYellHelloThere() {
        String word = "Hello there.";
        String expResult = "HELLO THERE.";
        String result = StringsExerciseA.yell(word);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testYellHelloThereShhhhh() {
        String word = "shhhhhhhhhhhh";
        String expResult = "SHHHHHHHHHHHH";
        String result = StringsExerciseA.yell(word);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testYellHelloThereAhhhhhh() {
        String word = "AAaAAAaAAAhhhh";
        String expResult = "AAAAAAAAAAHHHH";
        String result = StringsExerciseA.yell(word);
        assertEquals(expResult, result);
    }
    
}
