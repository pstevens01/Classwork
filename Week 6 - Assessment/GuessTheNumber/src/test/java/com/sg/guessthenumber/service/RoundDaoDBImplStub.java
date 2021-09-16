/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.model.Round;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coleogden
 */
public class RoundDaoDBImplStub implements RoundDao {

    @Override
    public Round addRound(Round round) {
        return round;
    }

    @Override
    public List<Round> getAllRoundsFromGame(int gameId) {
        Round testRound = new Round(1,1,"1234","e4p0");
        Round testRound2 = new Round(1,3,"1235","e3p0");
        List<Round> rounds = new ArrayList<>();
        rounds.add(testRound);
        rounds.add(testRound2);
        return rounds;
    }
    
}
