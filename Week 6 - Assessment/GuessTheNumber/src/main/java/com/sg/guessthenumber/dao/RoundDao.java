/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.model.Round;
import java.util.List;

/**
 *
 * @author paulstevens
 */
public interface RoundDao {
    
    /**
     * Store the round
     * @param round
     * @return stored round if successful
     */
    Round addRound(Round round);
	
    /**
     * Get all rounds for a game
     * @param gameId
     * @return list of rounds for a given game
     */
    List<Round> getAllRoundsFromGame(int gameId);
}
