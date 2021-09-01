/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mm.testing.dao.implementations;

import com.mm.testing.dao.MonsterDao;
import com.mm.testing.model.Monster;
import com.mm.testing.model.MonsterType;
import java.util.List;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author salajrawi
 */
public class AGoodMonsterDaoTest {
    
    MonsterDao dao = new AGoodMonsterDao();
    
    public AGoodMonsterDaoTest() {
        
    }

    @Test
    public void testAddGetUpdateMonster() {
        // Create monster obj
        Monster m = new Monster();
        m.setName("Booga");
        m.setType(MonsterType.YETI);
        m.setPeopleEaten(4);
        m.setFavoriteFood("Mutton Stir Fry");
        
        // Add monster to DAO
        dao.addMonster(1, m);
        
        // Get the monster from the DAO
        Monster retrievedMonster = dao.getMonster(1);
        
        // Check if the two objects are equal
        assertEquals(m.getName(), retrievedMonster.getName(),
                "Name should match");
        assertEquals(m.getType(), retrievedMonster.getType(),
                "Types should match");
        assertEquals(m.getPeopleEaten(), retrievedMonster.getPeopleEaten(),
                "Number of people should match");
        assertEquals(m.getFavoriteFood(), retrievedMonster.getFavoriteFood(),
                "Favorte food should match");
        
        // Create updated monster info
        Monster updatedMonster = new Monster();
        updatedMonster.setName("Archie");
        updatedMonster.setType(MonsterType.SWAMPTHING);
        updatedMonster.setPeopleEaten(5);
        updatedMonster.setFavoriteFood("Molded Cheesecake");
        
        // Update monster information
        dao.updateMonster(1, updatedMonster);
        
        // Retrieve updated monster
        retrievedMonster = dao.getMonster(1);
        
        // Check if the updated monster matches it's information
        assertEquals(updatedMonster.getName(), retrievedMonster.getName(),
                "Updated names should match");
        assertEquals(updatedMonster.getType(), retrievedMonster.getType(),
                "Updated types should match");
        assertEquals(updatedMonster.getPeopleEaten(), retrievedMonster.getPeopleEaten(),
                "Updated number of people should match");
        assertEquals(updatedMonster.getFavoriteFood(), retrievedMonster.getFavoriteFood(),
                "Updated favorte food should match");
    }
    
    @Test
    public void testAddGetAllMonsters() throws Exception {
        // Create two monsters
        Monster monster1 = new Monster();
        monster1.setName("Booga");
        monster1.setType(MonsterType.YETI);
        monster1.setPeopleEaten(4);
        monster1.setFavoriteFood("Mutton Stir Fry");
        
        Monster monster2 = new Monster();
        monster2.setName("Precious");
        monster2.setType(MonsterType.LIZARDMAN);
        monster2.setPeopleEaten(8);
        monster2.setFavoriteFood("Stinky Leg of Lamb");
        
        // Add monsters to the DAO
        dao.addMonster(1, monster1);
        dao.addMonster(2, monster2);
        
        // Get list of monsters
        List<Monster> allMonsters = dao.getAllMonsters();
        
        // Check if list is not null and is of size 2
        assertNotNull(allMonsters, "The list should be not null");
        assertEquals(2, allMonsters.size(), "List should be of size 2");
        
        // Check if the list contains both monsters
        assertTrue(dao.getAllMonsters().contains(monster1),
                "The list of monsters should contain Booga");
        assertTrue(dao.getAllMonsters().contains(monster2),
                "The list of monsters should contain Precious");
    }
    
    @Test
    public void testRemoveMonster() throws Exception {
        // Create two monsters
        Monster monster1 = new Monster();
        monster1.setName("Booga");
        monster1.setType(MonsterType.YETI);
        monster1.setPeopleEaten(4);
        monster1.setFavoriteFood("Mutton Stir Fry");
        
        Monster monster2 = new Monster();
        monster2.setName("Precious");
        monster2.setType(MonsterType.LIZARDMAN);
        monster2.setPeopleEaten(8);
        monster2.setFavoriteFood("Stinky Leg of Lamb");
        
        // Add monsters to the DAO
        dao.addMonster(1, monster1);
        dao.addMonster(2, monster2);
        
        // Remove the first monster - Booga
        Monster removedMonster = dao.removeMonster(1);
        
        // Check that the Booga was removed
        assertEquals(removedMonster, monster1, "The removed monster should be Booga");
        
        // Get list of monsters
        List<Monster> allMonsters = dao.getAllMonsters();
        
        // Check if list is not null and is of size 1
        assertNotNull(allMonsters, "The list should be not null");
        assertEquals(1, allMonsters.size(), "List should be of size 1");
        
        // List shouldn't contain Booga but should contain Precious
        assertFalse(allMonsters.contains(monster1), "The list should not have Booga");
        assertTrue(allMonsters.contains(monster2), "The list should have Precious");
        
        // Remove Precious from the monster list
        removedMonster = dao.removeMonster(2);
        
        // Check if the correct monster was removed
        assertEquals(removedMonster, monster2, "Removed monster should be Precious");
        
        // Get list of monsters to check contents again
        allMonsters = dao.getAllMonsters();
        
        // Checking the contents of the list - should be empty
        assertTrue(allMonsters.isEmpty(), "The list of monsters should be empty");
        
        // Try to retrieve both monster objects from the list - they should be null
        Monster retrievedMonster = dao.getMonster(1);
        assertNull(retrievedMonster, "Booga was removed, should be null");
        retrievedMonster = dao.getMonster(2);
        assertNull(retrievedMonster, "Precious was removed, should be null");   
    }
}
