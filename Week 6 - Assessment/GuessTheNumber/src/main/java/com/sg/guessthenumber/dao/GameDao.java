/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.model.Game;
import java.util.List;

/**
 *
 * @author paulstevens
 */
public interface GameDao {

    /**
     * Store game object
     * 
     * @param game
     * @return created game if successful
     */
    Game startGame(Game game);
	
    /**
     * Return all stored games
     * @return list of game objects
     */
    List<Game> getAllGames();
	
    /**
     * Get game by ID
     * @param gameId
     * @return game object
     */
    Game getGame(int gameId);

    /**
     * Update isFinished status of gameId to true
     * Person has won game and guessed correctly.
     * Congratulations, winner!
     * 
     * @param gameId
     * @return true if update runs successfully
     */
    public boolean winGame(int gameId);
}
