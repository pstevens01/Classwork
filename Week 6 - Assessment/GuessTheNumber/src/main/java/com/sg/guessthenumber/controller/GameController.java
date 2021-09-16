/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.controller;

import com.sg.guessthenumber.model.Game;
import com.sg.guessthenumber.model.Guess;
import com.sg.guessthenumber.model.Round;
import com.sg.guessthenumber.service.GameService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author coleogden
 */
@RestController
@RequestMapping("/guess-the-number-game")
public class GameController {
    
    @Autowired
    private final GameService service;
    
    public GameController(GameService gameService) {
        this.service = gameService;
    }
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game beginGame() {
        return service.beginGame();
    }
    
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round makeGuess(@RequestBody Guess guess) {
        return service.makeGuess(guess.getGuess(), guess.getGameId());
    }
    
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }
    
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable int gameId) {
        Game result = service.getGame(gameId);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> getRounds(@PathVariable int gameId) {
        List<Round> result = service.getRounds(gameId);
        return ResponseEntity.ok(result);
    }
}
