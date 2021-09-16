/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.model.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mitchell Cummins
 */
@Repository
public class RoundDaoDBImpl implements RoundDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Round addRound(Round round) {
        final String INSERT_ROUND = "INSERT INTO round(gameId, guess , guessResult, guessTime) VALUES(?,?,?,?)";
        jdbc.update(INSERT_ROUND,
                round.getGameId(),
                round.getGuess(),
                round.getGuessResult(),
                round.getGuessTime());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newId);
        return round;      
    }

    @Override
    public List<Round> getAllRoundsFromGame(int gameId) {
        final String SELECT_ALL_GAMES = "SELECT * FROM round WHERE gameId = ? ORDER BY roundId";
        return jdbc.query(SELECT_ALL_GAMES, new RoundMapper(), gameId);
    }
    
    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGameId(rs.getInt("gameId"));
            round.setGuess(String.valueOf(rs.getInt("guess")));
            round.setGuessTime(rs.getTimestamp("guessTime").toLocalDateTime());
            round.setGuessResult(rs.getString("guessResult"));
            return round;
        }
        
    }
}
