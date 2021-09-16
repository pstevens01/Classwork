/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.model.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mitchell Cummins
 * 
 */
@Repository
public class GameDaoDBImpl implements GameDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    @Transactional
    public Game startGame(Game game) {
        final String INSERT_GAME = "INSERT INTO game(gameAnswer) VALUE (?)";
        jdbc.update(INSERT_GAME,
                Integer.parseInt(game.getGameAnswer()));
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newId);
        return game;  
    }

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGame(int gameId) {
        final String SELECT_GAME = "SELECT * FROM game WHERE gameId = ?";
        Game game = jdbc.queryForObject(SELECT_GAME, new GameMapper(),gameId);
        return game;
    }

    @Override
    public boolean winGame(int gameId) {
        final String UPDATE_GAME_TO_WON = "UPDATE game SET isFinished = true WHERE gameId = ?";
        return jdbc.update(UPDATE_GAME_TO_WON, gameId) > 0;
    }
    
    public static final class GameMapper implements RowMapper<Game> {
        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setGameAnswer(String.valueOf(rs.getInt("gameAnswer")));
            game.setIsFinished(rs.getBoolean("isFinished"));
            return game;
        }
        
    }
}
