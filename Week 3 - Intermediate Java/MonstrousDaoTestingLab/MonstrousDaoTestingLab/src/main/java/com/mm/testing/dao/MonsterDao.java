/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mm.testing.dao;

import com.mm.testing.model.Monster;
import java.util.List;

public interface MonsterDao {
    public Monster addMonster(int id, Monster m);

    public Monster getMonster(int id);

    public List<Monster> getAllMonsters();

    public void updateMonster(int id, Monster m);

    public Monster removeMonster(int id);
}
