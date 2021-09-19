/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.dao;

import java.util.List;
import javabrewers.bespokejewelerblog.model.Creator;

/**
 *
 * @author Mitchell Cummins
 */
public interface CreatorDao {
    
    Creator addCreator(Creator creator);
    
    List<Creator> getAllCreators();
    
    List<Creator> getAllCanPostCreators();
    
    Creator getCreator(int creatorId);
}
