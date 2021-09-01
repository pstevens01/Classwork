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
public class LogicExerciseCTest {
    
    public LogicExerciseCTest() {
    }

    /**
     * Test of goWalky method, of class LogicExerciseC.
     */
    @Test
    public void testGoWalkyLightNoFlashRainingUmbrellaJustRight() {
        boolean isDark = true;
        boolean haveFlashlight = false;
        boolean isRaining = true;
        boolean haveUmbrella = true;
        int degreesFarenheit = 75;
        boolean expResult = false;
        boolean result = LogicExerciseC.goWalky(isDark, haveFlashlight, isRaining, haveUmbrella, degreesFarenheit);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGoWalkyDarkFlashNoRainNoUmbrellaJustRight() {
        boolean isDark = false;
        boolean haveFlashlight = true;
        boolean isRaining = false;
        boolean haveUmbrella = false;
        int degreesFarenheit = 50;
        boolean expResult = true;
        boolean result = LogicExerciseC.goWalky(isDark, haveFlashlight, isRaining, haveUmbrella, degreesFarenheit);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGoWalkyDarkNoFlashRainingNoUmbrellaTooCold() {
        boolean isDark = false;
        boolean haveFlashlight = false;
        boolean isRaining = false;
        boolean haveUmbrella = false;
        int degreesFarenheit = 30;
        boolean expResult = false;
        boolean result = LogicExerciseC.goWalky(isDark, haveFlashlight, isRaining, haveUmbrella, degreesFarenheit);
        assertEquals(expResult, result);
    }
}
