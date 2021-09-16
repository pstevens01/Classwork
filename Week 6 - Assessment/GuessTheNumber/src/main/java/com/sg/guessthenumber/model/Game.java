/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.model;

/**
 *
 * @author paulstevens
 */
public class Game {
    
    private int gameId;
    private String gameAnswer;
    private boolean isFinished;

    public Game(int gameId, String gameAnswer, boolean isFinished) {
        this.gameId = gameId;
        this.gameAnswer = gameAnswer;
        this.isFinished = isFinished;
    }

    public Game() {
    }


    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameAnswer() {
        return gameAnswer;
    }

    public void setGameAnswer(String gameAnswer) {
        this.gameAnswer = gameAnswer;
    }

    public boolean isIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.gameId;
        hash = 53 * hash + Integer.parseInt(this.gameAnswer);
        hash = 53 * hash + (this.isFinished ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (Integer.parseInt(this.gameAnswer) != Integer.parseInt(other.gameAnswer)) {
            return false;
        }
        if (this.isFinished != other.isFinished) {
            return false;
        }
        return true;
    }
    
    
}
