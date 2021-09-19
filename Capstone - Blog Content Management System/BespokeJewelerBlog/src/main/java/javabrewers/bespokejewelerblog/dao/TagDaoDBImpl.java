/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabrewers.bespokejewelerblog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javabrewers.bespokejewelerblog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Mitchell Cummins
 */
public class TagDaoDBImpl implements TagDao{
    @Autowired
    JdbcTemplate jdbc;
    
    //adds a tag to the data base.
    @Override
    public Tag addTag(Tag tag) {
        final String INSERT_TAG = "INSERT INTO tag(tagName) VALUE (?)";
        jdbc.update(INSERT_TAG,
                tag.getTagName());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        tag.setTagId(newId);
        return tag;  
    }

    //adds the tagId and the postId to the posttag database, takes in both postId and tagId in that order.
    @Override
    public void addPostTag(int postId,int tagId) {
        final String INSERT_TAG = "INSERT INTO posttag(postId,tagId) VALUE (?,?)";
        jdbc.update(INSERT_TAG,
                tagId,
                postId);
    }
    
    //gets a specific tag by taking in the string associated with that tag.
    @Override
    public Tag getTag(String tagName) {
        final String SELECT_TAG = "SELECT * FROM tag WHERE tagName = ?";
        Tag tag = jdbc.queryForObject(SELECT_TAG, new TagMapper(), tagName);
        return tag;
    }

    //returns a specific tag by taking in the id associated with that tag.
    @Override
    public Tag getTag(int tagId) {
        final String SELECT_TAG = "SELECT * FROM tag WHERE tagId = ?";
        Tag tag = jdbc.queryForObject(SELECT_TAG, new TagMapper(), tagId);
        return tag;
    }
    
    //returns a list of all tags.
    @Override
    public List<Tag> getAllTags(){
        final String SELECT_ALL_TAGS = "SELECT * FROM tag";
        return jdbc.query(SELECT_ALL_TAGS,new TagMapper());
    }
    
    @Override
    public List<Tag> getAllTags(int postId) {
        final String SELECT_ALL_TAGS = "SELECT * FROM tag t inner join posttag pt"
                + "on t.tagId = pt.tagId"
                + "WHERE pt.postId = ?";
        List<Tag> tags = jdbc.query(SELECT_ALL_TAGS, new TagMapper(),postId);
	return tags;
    }
    
    
    
    public static final class TagMapper implements RowMapper<Tag> {
        
        @Override
        public Tag mapRow(ResultSet rs, int index) throws SQLException{
            Tag tag = new Tag();
            tag.setTagId(rs.getInt("tagId"));
            tag.setTagName(rs.getString("tagName"));
            return tag;
        }
    }
}
