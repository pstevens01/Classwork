/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.model.Game;
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
public class GameServiceImplTest {
    
    GameService service;
    GameDao gameDao;
    RoundDao roundDao;
    
    public GameServiceImplTest() {
        gameDao = new GameDaoDBImplStub();
        roundDao = new RoundDaoDBImplStub();
        service = new GameServiceImpl(gameDao, roundDao);
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
    public void addNewGame() {
        Game response = service.beginGame();
        assertNotNull(response.getGameAnswer());
        assertNotNull(response.getGameId());
        assertFalse(response.isIsFinished());
    }

    @Test
    public void makeGuessExactGuess() {
        Round response = service.makeGuess(1, 1234);
        assertEquals(response.getGameId(),1, "Checking gameId.");
        assertEquals(response.getGuess(),"1234", "Checking guess.");
        assertEquals(response.getGuessResult(),"e4p0", "Checking guess result.");
        assertEquals(response.getRoundId(), 1, "Checking round Id.");
        assertTrue(response.getGuessTime().isBefore(LocalDateTime.of(3000, 12, 31, 0, 0))); // went to the Year 3000
        
        //TODO: Assure finishing right guess converts is finished to true
        Game game = service.getGame(1);
        assertTrue(game.isIsFinished());
    }

    @Test
    public void makeGuessExactMatchesNoPartialMatches() {
        Round response = service.makeGuess(1, 1235);
        assertEquals(response.getGuessResult(),"e3p0", "Result should be 3 exact, 0 partial.");
    }

    @Test
    public void makeGuessNoExactMatchesPartialPatches() {
        Round response = service.makeGuess(1, 4321);
        assertEquals(response.getGuessResult(),"e0p4", "Result should be 4 partial, 0 exact");
    }

    @Test
    public void makeGuessExactMatchesPartialMatches() {
        Round response = service.makeGuess(1, 1243);
        assertEquals(response.getGuessResult(),"e2p2", "Result should be 2 exact, 2 partial");
    }

    @Test
    public void makeGuessNoExactMatchesNoPartialMatches() {
        Round response = service.makeGuess(1, 6789);
        assertEquals(response.getGuessResult(),"e0p0", "Result should be 0 exact, 0 partial");
    }

    @Test
    public void getAllGames() {
        List<Game> games = service.getAllGames();
        assertEquals(games.size(),2,"Size of games array list should be 2.");
    }

    @Test
    public void getRoundsOfGame() {
        List<Round> rounds = service.getRounds(1);
        assertEquals(rounds.size(),2,"Size of rounds array list should be 2.");
    }
    
}
