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
public class ArrayExerciseBTest {
    
    public ArrayExerciseBTest() {
    }

    /**
     * Test of multiplyAll method, of class ArrayExerciseB.
     */
    @Test
    public void testMultiplyAllOneElement() {
        System.out.println("multiplyAll");
        int multiplier = 3;
        int[] numbers = {1};
        int[] expResult = {3};
        int[] result = ArrayExerciseB.multiplyAll(multiplier, numbers);
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testMultiplyAll5Negative() {
        System.out.println("multiplyAll");
        int multiplier = 5;
        int[] numbers = {-10, -5, -2, -1};
        int[] expResult = {-50, -25, -10, -5};
        int[] result = ArrayExerciseB.multiplyAll(multiplier, numbers);
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testMultiplyAll7WithZero() {
        System.out.println("multiplyAll");
        int multiplier = 7;
        int[] numbers = {0, 3, 7};
        int[] expResult = {0, 21, 49};
        int[] result = ArrayExerciseB.multiplyAll(multiplier, numbers);
        assertArrayEquals(expResult, result);
    }    
}
