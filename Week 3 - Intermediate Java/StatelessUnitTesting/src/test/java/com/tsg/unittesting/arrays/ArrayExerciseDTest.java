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
public class ArrayExerciseDTest {
    
    public ArrayExerciseDTest() {
    }

    /**
     * Test of pointFree method, of class ArrayExerciseD.
     */
    @Test
    public void testPointFreeNonNegative() {
        double[] numbers = {0.22, 1.1, 3.223};
        int expResult = 3223;
        int result = ArrayExerciseD.pointFree(numbers);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPointFreeNegativeAndPositive() {
        double[] numbers = {-0.234, 2.45, 0.0034, -1.79};
        int expResult = 245;
        int result = ArrayExerciseD.pointFree(numbers);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testPointFreeNegative() {
        double[] numbers = {-9.9, -390, -123, -0.1856};
        int expResult = -99;
        int result = ArrayExerciseD.pointFree(numbers);
        assertEquals(expResult, result);
    }
}
