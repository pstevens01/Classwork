/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mm.testing.dao.implementations.buggy;

import com.mm.testing.dao.MonsterDao;
import com.mm.testing.model.Monster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BadMonsterDaoA implements MonsterDao{
    Map<Integer, Monster> monsters = new HashMap<>();
    int id;
    
    @Override
    public Monster addMonster(int i, Monster m) {
        return monsters.put(id, m);
    }

    @Override
    public Monster getMonster(int id) {
       Monster m = monsters.get(id);
       return m;
    }

    @Override
    public List<Monster> getAllMonsters() {
        List<Monster> monsterList = new ArrayList<>();
        monsterList.addAll(monsters.values());
        return monsterList;
    }

    @Override
    public void updateMonster(int id, Monster m) {
        monsters.replace(id, m);
    }

    @Override
    public Monster removeMonster(int id) {
        Monster m = monsters.remove(id);
        return m;
    }
}
