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
public class LogicExerciseATest {
    
    public LogicExerciseATest() {
    }

    /**
     * Test of friendlyGreeting method, of class LogicExerciseA.
     */
    @Test
    public void testFriendlyGreetingNotFriend() {
        String visitorName = "Goofus";
        boolean isFriend = false;
        String expResult = "hi";
        String result = LogicExerciseA.friendlyGreeting(visitorName, isFriend);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFriendlyGreetingFriend() {
        String visitorName = "Gallant";
        boolean isFriend = true;
        String expResult = "Hello, Gallant!";
        String result = LogicExerciseA.friendlyGreeting(visitorName, isFriend);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFriendlyGreetingNull() {
        String visitorName = null;
        boolean isFriend = false;
        String expResult = "...";
        String result = LogicExerciseA.friendlyGreeting(visitorName, isFriend);
        assertEquals(expResult, result);
    }
}
