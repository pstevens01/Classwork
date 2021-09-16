/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.model.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author coleogden
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoundDaoDBImplTest {

    private RoundDao roundDao;

    public RoundDaoDBImplTest() {
        roundDao = new RoundDaoDBImpl();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddRound() {
        Round testRound = new Round();
        testRound.setGameId(1);
        testRound.setGuess("1234");
        String testResponse = "Test response";
        testRound.setGuessResult(testResponse);
        LocalDateTime testTime = LocalDateTime.now();
        testRound.setGuessTime(testTime);
        testRound.setRoundId(1);

        Round result = roundDao.addRound(testRound);
        assertEquals(result.getGameId(), testRound.getGameId(), "Checking Game ID.");
        assertEquals(result.getGuess(), testRound.getGuess(), "Checking guess.");
        assertEquals(result.getGuessResult(), testResponse, "Checking test response.");
        assertEquals(result.getGuessTime(), testTime, "Checking guess time.");
        assertEquals(result.getRoundId(), testRound.getRoundId(), "Checking round ID.");
    }

    @Test
    public void testGetAllRoundsFromGame() {
        Round testRound1 = new Round();
        testRound1.setGameId(1);
        testRound1.setRoundId(1);
        testRound1.setGuess("1234");
        testRound1.setGuessTime(LocalDateTime.now());
        roundDao.addRound(testRound1);

        Round testRound2 = new Round();
        testRound2.setGameId(1);
        testRound2.setRoundId(2);
        testRound2.setGuess("1235");
        testRound2.setGuessTime(LocalDateTime.now());
        roundDao.addRound(testRound2);

        List<Round> response = roundDao.getAllRoundsFromGame(1);

        assertEquals(response.size(), 2, "Response list size should be 2.");
        assertTrue(response.contains(testRound1), "Response should contain first round.");
        assertTrue(response.contains(testRound2), "Response should contain second round.");

    }

}
