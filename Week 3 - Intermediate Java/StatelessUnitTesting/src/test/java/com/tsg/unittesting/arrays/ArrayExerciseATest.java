/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.unittesting.arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Paul
 */
public class ArrayExerciseATest {
    
    public ArrayExerciseATest() {
    }

    /**
     * Test of maxOfArray method, of class ArrayExerciseA.
     */
    @Test
    public void testMaxOfArray() {
        int[] numbers = {1};
        int expResult = 1;
        int result = ArrayExerciseA.maxOfArray(numbers);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMaxOfArrayThreeElements() {
        int[] numbers = {3, 4, 5};
        int expResult = 5;
        int result = ArrayExerciseA.maxOfArray(numbers);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMaxOfArrayNegativeElements() {
        int[] numbers = {-9000, -700, -50, -3};
        int expResult = -3;
        int result = ArrayExerciseA.maxOfArray(numbers);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testMaxOfArrayNegativeAndPositive() {
        int[] numbers = {-20, -3, -7, 0, 3, 14, 18, 21, 36};
        int expResult = 36;
        int result = ArrayExerciseA.maxOfArray(numbers);
        assertEquals(expResult, result);
    }
    
}
