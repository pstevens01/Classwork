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
public class ArrayExerciseCTest {
    
    public ArrayExerciseCTest() {
    }


    /**
     * Test of stringThemTogether method, of class ArrayExerciseC.
     */
    @Test
    public void testStringThemTogetherLong() {
        int[] nums = {1, 3, 3, 7};
        ArrayExerciseC instance = new ArrayExerciseC();
        String expResult = "1337";
        String result = instance.stringThemTogether(nums);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testStringThemTogetherLonger() {
        int[] nums = {1, 33, 555, 7777, 99999};
        ArrayExerciseC instance = new ArrayExerciseC();
        String expResult = "133555777799999";
        String result = instance.stringThemTogether(nums);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testStringThemTogetherNull() {
        int[] nums = {};
        ArrayExerciseC instance = new ArrayExerciseC();
        String expResult = "";
        String result = instance.stringThemTogether(nums);
        assertEquals(expResult, result);
    }
}
