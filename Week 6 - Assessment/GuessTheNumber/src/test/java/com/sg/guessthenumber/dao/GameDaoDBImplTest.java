/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.model.Game;
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
public class GameDaoDBImplTest {
    
    private GameDao testDao;
    
    public GameDaoDBImplTest() {
        testDao = new GameDaoDBImpl();
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
    public void addGame() {
        Game testGame = new Game();
        testGame.setGameId(1);
        testGame.setGameAnswer("1234");
        testGame.setIsFinished(false);

        Game result = testDao.startGame(testGame);

        assertEquals(result.getGameId(), testGame.getGameId(), "Checking game ID.");
        assertEquals(result.getGameAnswer(), testGame.getGameAnswer(), "Checking game answer.");
        assertEquals(result.isIsFinished(), testGame.isIsFinished(), "Checking is finished.");
    }

    @Test
    public void getGame() {
        Game testGame = new Game();
        testGame.setGameId(1);
        testGame.setGameAnswer("1234");
        testGame.setIsFinished(false);

        testDao.startGame(testGame);
        Game result = testDao.getGame(1);
        
        assertEquals(result.getGameId(), testGame.getGameId(), "Checking game ID.");
        assertEquals(result.getGameAnswer(), testGame.getGameAnswer(), "Checking game answer.");
        assertEquals(result.isIsFinished(), testGame.isIsFinished(), "Checking is finished.");
    }

    @Test
    public void getAllGames() {
        Game testGame = new Game();
        testGame.setGameId(1);
        testGame.setGameAnswer("1234");
        testGame.setIsFinished(false);

        testDao.startGame(testGame);
        testDao.getGame(1);
        
        Game testGame2 = new Game();
        testGame2.setGameId(2);
        testGame2.setGameAnswer("1235");
        testGame2.setIsFinished(true);

        testDao.startGame(testGame2);
        List<Game> result = testDao.getAllGames();
        
        assertEquals(result.size(), 2, "Result list size should be 2.");
        assertTrue(result.contains(testGame), "Result should contain first test game.");
        assertTrue(result.contains(testGame2), "Result should contain second test game.");
    }
    
}
