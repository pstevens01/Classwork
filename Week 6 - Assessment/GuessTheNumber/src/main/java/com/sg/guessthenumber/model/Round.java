/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author paulstevens
 */
public class Round {
    
    private int roundId;
    private int gameId;
    private String guess;
    private LocalDateTime guessTime;
    private String guessResult;

    public Round(int roundId, int gameId, String guess, String guessResult) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.guess = guess;
        this.guessTime = LocalDateTime.now();
        this.guessResult = guessResult;
    }

    public Round() {    
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }

    public String getGuessResult() {
        return guessResult;
    }

    public void setGuessResult(String guessResult) {
        this.guessResult = guessResult;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.roundId;
        hash = 71 * hash + this.gameId;
        hash = 71 * hash + Integer.parseInt(this.guess);
        hash = 71 * hash + Objects.hashCode(this.guessTime);
        hash = 71 * hash + Objects.hashCode(this.guessResult);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (Integer.parseInt(this.guess) != Integer.parseInt(other.guess)) {
            return false;
        }
        if (!Objects.equals(this.guessResult, other.guessResult)) {
            return false;
        }
        if (!Objects.equals(this.guessTime, other.guessTime)) {
            return false;
        }
        return true;
    }
    
    
}
