/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.model.Game;
import com.sg.guessthenumber.model.Round;
import java.util.List;

/**
 *
 * @author coleogden
 */
public interface GameService {
    public Game beginGame();
    public Round makeGuess(int guess, int gameId);
    public List<Game> getAllGames();
    public Game getGame(int gameId);
    public List<Round> getRounds(int gameId);
}
