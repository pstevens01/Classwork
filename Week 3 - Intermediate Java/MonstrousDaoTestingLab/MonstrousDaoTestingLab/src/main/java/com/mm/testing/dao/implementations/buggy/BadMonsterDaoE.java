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

public class BadMonsterDaoE implements MonsterDao{
     Map<Integer, Monster> monsters = new HashMap<>();
    
    @Override
    public Monster addMonster(int id, Monster m) {
        return monsters.put(id, m);
    }

    @Override
    public Monster getMonster(int id) {
       return monsters.remove(id);
    }

    @Override
    public List<Monster> getAllMonsters() {
        return new ArrayList<>(monsters.values());
    }

    @Override
    public void updateMonster(int id, Monster m) {
        if(monsters.containsKey(id)){
            monsters.replace(id, m);
        }
    }

    @Override
    public Monster removeMonster(int id) {
        Monster toRemove = monsters.get(id);
        monsters.remove(id);
        return toRemove;
    }
}
