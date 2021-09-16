/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.model.Game;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coleogden
 */
public class GameDaoDBImplStub implements GameDao {

    Game testGame = new Game(1,"1234",false);
    Game testGame2 = new Game(2,"2345",true);
    
    @Override
    public Game startGame(Game game) {
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> response = new ArrayList<>();
        response.add(testGame);
        response.add(testGame2);
        return response;
    }

    @Override
    public Game getGame(int gameId) {
        if(gameId == testGame.getGameId()) {
            return testGame;
        }
        return null;
    }

    @Override
    public boolean winGame(int gameId) {
        if(gameId == testGame.getGameId()) {
            testGame.setIsFinished(true);
            return true;
        }
        if(gameId == testGame2.getGameId()) {
            testGame2.setIsFinished(true);
            return true;
        }
        return false;
    }
    
}
