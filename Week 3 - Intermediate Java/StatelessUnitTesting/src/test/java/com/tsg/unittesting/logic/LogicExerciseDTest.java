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
public class LogicExerciseDTest {
    
    public LogicExerciseDTest() {
    }

    /**
     * Test of isFirstTheFirst method, of class LogicExerciseD.
     */
    @Test
    public void testIsFirstTheFirst_a_b() {
        char letterOne = 'a';
        char letterTwo = 'b';
        boolean expResult = true;
        boolean result = LogicExerciseD.isFirstTheFirst(letterOne, letterTwo);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsFirstTheFirst_O_X() {
        char letterOne = 'O';
        char letterTwo = 'X';
        boolean expResult = true;
        boolean result = LogicExerciseD.isFirstTheFirst(letterOne, letterTwo);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIsFirstTheFirst_Z_z() {
        char letterOne = 'Z';
        char letterTwo = 'z';
        boolean expResult = false;
        boolean result = LogicExerciseD.isFirstTheFirst(letterOne, letterTwo);
        assertEquals(expResult, result);
    }
}
