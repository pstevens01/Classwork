package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.model.Game;
import com.sg.guessthenumber.model.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author coleogden
 */
@Component
public class GameServiceImpl implements GameService {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public GameServiceImpl(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @Override
    public Game beginGame() {
        String answer = generateNumber();
        Game game = new Game();
        game.setGameAnswer(answer);
        return gameDao.startGame(game);
    }

    @Override
    public Round makeGuess(int guess, int gameId) {
        Round round = new Round();
        String answer = gameDao.getGame(gameId).getGameAnswer();
        String guessResult = getGuessResult(String.valueOf(guess), answer);
        if(guessResult.contains("e:4")) {
            gameDao.winGame(gameId);
        }

        round.setGameId(gameId);
        round.setGuess(Integer.toString(guess));
        round.setGuessResult(guessResult);
        round.setGuessTime(LocalDateTime.now());
        return roundDao.addRound(round);
    }

    @Override
    public List<Game> getAllGames() {
        return gameDao.getAllGames().stream().map(game -> {
            if (!game.isIsFinished()) {
                game.setGameAnswer("");
            }
            return game;
        }).collect(Collectors.toList());
    }

    @Override
    public Game getGame(int gameId) {
        Game game = gameDao.getGame(gameId);
        if (!game.isIsFinished()) {
            game.setGameAnswer("");
        }
        return game;
    }

    @Override
    public List<Round> getRounds(int gameId) {
        return roundDao.getAllRoundsFromGame(gameId);
    }

    private String generateNumber() {
        List<Integer> digits = new ArrayList<>();

        digits.add(getRandomInRange(1, 9));
        int currentDigit;
        do {
            currentDigit = getRandomInRange(0, 9);
            if (!digits.contains(currentDigit)) {
                digits.add(currentDigit);
            }
        } while (digits.size() < 4);

        return digits.toString().replaceAll("[^\\d]", "");
    }

    private int getRandomInRange(int min, int max) {
        Random rng = new Random();
        return rng.nextInt((max - min) + 1) + min;
    }

    private String getGuessResult(String guess, String answer) {
        int e = 0;
        int p = 0;

        if (answer.length() < 4) {
            answer = String.format("%04d", Integer.parseInt(answer));
        }
        if (guess.length() < 4) {
            guess = String.format("%04d", Integer.parseInt(guess));
        }

        for (int i = 0; i < guess.length(); i++) {
            for (int j = 0; j < answer.length(); j++) {
                if (guess.charAt(i) == answer.charAt(j)) {
                    if (i == j) {
                        e++;
                    } else {
                        p++;
                    }
                }
            }
        }

        return String.format("e:%s p:%s", e, p);
    }
}
