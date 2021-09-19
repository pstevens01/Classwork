/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.dao;

import java.util.List;
import javabrewers.bespokejewelerblog.model.Tag;

/**
 *
 * @author Mitchell Cummins
 */
public interface TagDao {
    Tag addTag(Tag tag);
    
    void addPostTag(int postId,int tagId);
    
    Tag getTag(String Tag);
    
    Tag getTag(int tagId);
    
    List<Tag> getAllTags();
    
    public List<Tag> getAllTags(int postId);
}
